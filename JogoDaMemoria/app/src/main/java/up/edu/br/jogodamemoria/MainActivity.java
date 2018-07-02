package up.edu.br.jogodamemoria;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Conexao(getApplicationContext(), "jogador.db", null,1);

        Jogador jogador = new JogadorDao().primeiroDoRanking();

        if(jogador != null){
            if( new JogadorDao().primeiroLugar == null){
                new JogadorDao().primeiroLugar = new JogadorDao().primeiroDoRanking();
            }
        }
        
        Button btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, SelecionarActivity.class);
                startActivity(it);
            }
        });

        Button btnRanking = (Button)findViewById(R.id.btnRanking);
        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(it);
            }
        });
    }



}
