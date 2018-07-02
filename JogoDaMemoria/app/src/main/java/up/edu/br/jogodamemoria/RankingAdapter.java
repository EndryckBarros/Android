package up.edu.br.jogodamemoria;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class RankingAdapter extends BaseAdapter {

    private List<Jogador> jogadores;
    Activity act;

    public RankingAdapter(List<Jogador> jogadores, Activity act) {
        this.jogadores = jogadores;
        this.act = act;
    }

    @Override
    public int getCount() {
        return this.jogadores.size();
    }

    @Override
    public Object getItem(int i) {
        return this.jogadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = act.getLayoutInflater().inflate(R.layout.ranking_adapter,viewGroup,false);

        TextView nome = (TextView)v.findViewById(R.id.txtNome);
        TextView vitorias = (TextView)v.findViewById(R.id.txtVitorias);
        TextView derrotas = (TextView)v.findViewById(R.id.txtDerrotas);
        TextView posicao = (TextView)v.findViewById(R.id.txtPosicao);
        ImageView image = (ImageView)v.findViewById(R.id.imagePerfil);

        Jogador j = jogadores.get(i);

        nome.setText(j.getNome());
            vitorias.setText(String.valueOf(j.getVitorias()));
            derrotas.setText(String.valueOf(j.getDerrotas()));
            posicao.setText(String.valueOf(j.getPosicao()));

        ByteArrayInputStream imageStream = new ByteArrayInputStream(j.getImagem());
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        image.setImageBitmap(bitmap);

        return v;
    }

    public void remove(Jogador jogador) {
        jogadores.remove(jogador);
    }
}
