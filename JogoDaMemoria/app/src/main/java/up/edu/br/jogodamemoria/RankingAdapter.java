package up.edu.br.jogodamemoria;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public void remove(Jogador jogador) {
        jogadores.remove(jogador);
    }
}
