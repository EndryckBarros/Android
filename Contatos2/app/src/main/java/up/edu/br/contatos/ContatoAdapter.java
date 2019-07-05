package up.edu.br.contatos;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aluno on 04/06/2018.
 */

public class ContatoAdapter extends BaseAdapter {

    private List<Contato> contatos;
    Activity act;

    public ContatoAdapter(List<Contato> contatos, Activity act) {
        this.contatos = contatos;
        this.act = act;
    }


    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.contatos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = act.getLayoutInflater().inflate(R.layout.contato_adapter,viewGroup,false);

        TextView textView1 = (TextView) v.findViewById(R.id.textView);
        TextView textView2 = (TextView) v.findViewById(R.id.textView2);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        Contato c = contatos.get(i);

        textView1.setText(c.getNome());
        textView2.setText(c.getNumero());

        if (c.getTipo().equals("Celular")){
            imageView.setImageResource(R.drawable.ic_smartphone_black_24dp);
        }
        if (c.getTipo().equals("Casa")){
            imageView.setImageResource(R.drawable.ic_home_black_24dp);
        }
        if (c.getTipo().equals("Trabalho")){
            imageView.setImageResource(R.drawable.ic_work_black_24dp);
        }

        return v;
    }

    public void remove(Contato c){
        contatos.remove(c);
    }
}
