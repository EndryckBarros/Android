package up.edu.br.jogodamemoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelecionarActivity extends AppCompatActivity {

    ArrayList<Jogador> jogadores = new ArrayList<>();
    int aux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar);

        ListView listaJogadores = (ListView)findViewById(R.id.lv_Jogadores);

        ArrayAdapter<Jogador> arrayAdapterJogadores = new ArrayAdapter<Jogador>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice);
        arrayAdapterJogadores.addAll(new JogadorDao().listar());
        listaJogadores.setAdapter(arrayAdapterJogadores);

        listaJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Jogador j = (Jogador) adapterView.getItemAtPosition(posicao);
                Toast.makeText(getApplicationContext(),"Nome: "+j.getNome().toString(),Toast.LENGTH_SHORT).show();
                jogadores.add(j);
                aux+=1;
                 if(aux > 2){
                     Toast.makeText(getApplicationContext(),"É só dois o seu CAVALO!",Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }


}
