package up.edu.br.jogodamemoria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, GameActivity.class);
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
