package up.edu.br.jogodamemoria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelecionarActivity extends AppCompatActivity {

    Partida partida = new Partida();
    Jogador[] jogadores = new Jogador[2];
    boolean oneClick = false;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar);

        ListView listaJogadores = (ListView)findViewById(R.id.lv_Jogadores);
        final TextView nome1 = (TextView)findViewById(R.id.tv_Nome1);
        final TextView nome2 = (TextView)findViewById(R.id.tv_Nome2);
        final Spinner spTema = (Spinner)findViewById(R.id.sp_tema);

        ImageButton btnIniciar = (ImageButton)findViewById(R.id.ib_iniciar);
            btnIniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(SelecionarActivity.this, GameActivity.class);

                    //NÃO INICIA SE OS JOGADORES NÃO FOREM SELECIONADOS
                    if (cont > 1){
                        partida.setTema(spTema.getSelectedItem().toString());
                        it.putExtra("partida", partida);
                        startActivity(it);
                    }else {
                        Toast.makeText(getApplicationContext(),"Selecione os jogadores",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        ImageButton btnVoltar= (ImageButton)findViewById(R.id.ib_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SelecionarActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        ArrayAdapter<Jogador> arrayAdapterJogadores = new ArrayAdapter<Jogador>(getApplicationContext(),android.R.layout.simple_list_item_1);
        arrayAdapterJogadores.addAll(new JogadorDao().listar());
        listaJogadores.setAdapter(arrayAdapterJogadores);

        listaJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Jogador j = (Jogador) adapterView.getItemAtPosition(posicao);
                Toast.makeText(getApplicationContext(),"Nome: "+j.getNome().toString(),Toast.LENGTH_SHORT).show();
                cont ++;

                if(oneClick == false){
                    nome1.setText(j.getNome());
                    partida.setJogador1(j);
                    jogadores[0] = j;
                    oneClick = true;
                }else{
                    nome2.setText(j.getNome());
                    partida.setJogador2(j);
                    jogadores[1] = j;
                    oneClick = false;
                }
            }
        });
    }
}
