package up.edu.br.jogodamemoria;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Conexao(getApplicationContext(), "jogador.db", null,1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RankingActivity.this, JogadorActivity.class);
                startActivity(it);
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        ListView listaJogadores = (ListView) findViewById(R.id.listaJogadores);
        RankingAdapter rankingAdapter = new RankingAdapter(new JogadorDao().listar(), this);
        listaJogadores.setAdapter(rankingAdapter);

        listaJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Jogador j = (Jogador) adapterView.getItemAtPosition(posicao);

                Intent it = new Intent(RankingActivity.this, JogadorActivity.class);

                it.putExtra("jogador", j);

                startActivity(it);
            }
        });

        listaJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view,final int posicao, long l) {

                new AlertDialog.Builder(RankingActivity.this);
                AlertDialog.Builder alert = new AlertDialog.Builder(RankingActivity.this);
                alert.setMessage("Deseja Realmente Excluir?");
                alert.setCancelable(false);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Jogador jogador = (Jogador) adapterView.getItemAtPosition(posicao);

                                new JogadorDao().excluir(jogador);

                                ((RankingAdapter) adapterView.getAdapter()).remove(jogador);
                                ((RankingAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                            }
                        });

                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                alert.show();
                return true;
            }
        });
    }

}
