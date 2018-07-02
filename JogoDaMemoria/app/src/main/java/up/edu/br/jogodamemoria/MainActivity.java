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

        Jogador jogador = new JogadorDao().notificacao();
        if(jogador != null){
            gerarNotificação(jogador);
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

    public void gerarNotificação(Jogador j){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sync_black_24dp)
                        .setContentTitle("O Primeiro lugar acaba de ser ultrapassado")
                        .setContentText("1º " + j.getNome())
                        .setAutoCancel(true);

        // Cria o intent que irá chamar a atividade a ser aberta quando clicar na notifição
        Intent resultIntent = new Intent(MainActivity.this, RankingActivity.class);

        //PendingIntent é "vinculada" a uma notification para abrir a intent
        PendingIntent resultPendingIntent = PendingIntent.
                getActivity(MainActivity.this, 0, resultIntent, 0);

        //associa o intent na notificação
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //gera a notificação
        mNotificationManager.notify(99, mBuilder.build());
    }

}
