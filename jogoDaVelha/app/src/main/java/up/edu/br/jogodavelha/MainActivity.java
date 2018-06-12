package up.edu.br.jogodavelha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean jogador = true;
    ImageView v1;
    ImageView v2;
    ImageView v3;
    ImageView v4;
    ImageView v5;
    ImageView v6;
    ImageView v7;
    ImageView v8;
    ImageView v9;

    public void iniciar(){
        v1.setTag(R.drawable.vazio);
        v2.setTag(R.drawable.vazio);
        v3.setTag(R.drawable.vazio);
        v4.setTag(R.drawable.vazio);
        v5.setTag(R.drawable.vazio);
        v6.setTag(R.drawable.vazio);
        v7.setTag(R.drawable.vazio);
        v8.setTag(R.drawable.vazio);
        v9.setTag(R.drawable.vazio);

        v1.setImageResource(R.drawable.vazio);
        v2.setImageResource(R.drawable.vazio);
        v3.setImageResource(R.drawable.vazio);
        v4.setImageResource(R.drawable.vazio);
        v5.setImageResource(R.drawable.vazio);
        v6.setImageResource(R.drawable.vazio);
        v7.setImageResource(R.drawable.vazio);
        v8.setImageResource(R.drawable.vazio);
        v9.setImageResource(R.drawable.vazio);

    }

    public void validarJogada(){
        if(     (Integer)v1.getTag() == R.drawable.circulo &&
                (Integer)v2.getTag() == R.drawable.circulo &&
                (Integer)v3.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v4.getTag() == R.drawable.circulo &&
                (Integer)v5.getTag() == R.drawable.circulo &&
                (Integer)v6.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v7.getTag() == R.drawable.circulo &&
                (Integer)v8.getTag() == R.drawable.circulo &&
                (Integer)v9.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v1.getTag() == R.drawable.circulo &&
                (Integer)v4.getTag() == R.drawable.circulo &&
                (Integer)v7.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v2.getTag() == R.drawable.circulo &&
                (Integer)v5.getTag() == R.drawable.circulo &&
                (Integer)v8.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v3.getTag() == R.drawable.circulo &&
                (Integer)v6.getTag() == R.drawable.circulo &&
                (Integer)v9.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v1.getTag() == R.drawable.circulo &&
                (Integer)v5.getTag() == R.drawable.circulo &&
                (Integer)v9.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v3.getTag() == R.drawable.circulo &&
                (Integer)v5.getTag() == R.drawable.circulo &&
                (Integer)v7.getTag() == R.drawable.circulo){

            Toast.makeText(getApplicationContext(), "Circulo Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v1.getTag() == R.drawable.x &&
                (Integer)v2.getTag() == R.drawable.x &&
                (Integer)v3.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v4.getTag() == R.drawable.x &&
                (Integer)v5.getTag() == R.drawable.x &&
                (Integer)v6.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v7.getTag() == R.drawable.x &&
                (Integer)v8.getTag() == R.drawable.x &&
                (Integer)v9.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v1.getTag() == R.drawable.x &&
                (Integer)v4.getTag() == R.drawable.x &&
                (Integer)v7.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v2.getTag() == R.drawable.x &&
                (Integer)v5.getTag() == R.drawable.x &&
                (Integer)v8.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v3.getTag() == R.drawable.x &&
                (Integer)v6.getTag() == R.drawable.x &&
                (Integer)v9.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v1.getTag() == R.drawable.x &&
                (Integer)v5.getTag() == R.drawable.x &&
                (Integer)v9.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
        if(     (Integer)v3.getTag() == R.drawable.x &&
                (Integer)v5.getTag() == R.drawable.x &&
                (Integer)v7.getTag() == R.drawable.x){

            Toast.makeText(getApplicationContext(), "X Ganhou", Toast.LENGTH_LONG).show();
            iniciar();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1 = (ImageView)findViewById(R.id.imageView1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v1.setImageResource(R.drawable.circulo);
                    v1.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v1.setImageResource(R.drawable.x);
                    v1.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v2 = (ImageView)findViewById(R.id.imageView2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v2.setImageResource(R.drawable.circulo);
                    v2.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v2.setImageResource(R.drawable.x);
                    v2.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v3 = (ImageView)findViewById(R.id.imageView3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v3.setImageResource(R.drawable.circulo);
                    v3.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v3.setImageResource(R.drawable.x);
                    v3.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v4 = (ImageView)findViewById(R.id.imageView4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v4.setImageResource(R.drawable.circulo);
                    v4.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v4.setImageResource(R.drawable.x);
                    v4.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v5 = (ImageView)findViewById(R.id.imageView5);
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v5.setImageResource(R.drawable.circulo);
                    v5.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v5.setImageResource(R.drawable.x);
                    v5.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v6 = (ImageView)findViewById(R.id.imageView6);
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v6.setImageResource(R.drawable.circulo);
                    v6.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v6.setImageResource(R.drawable.x);
                    v6.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v7 = (ImageView)findViewById(R.id.imageView7);
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v7.setImageResource(R.drawable.circulo);
                    v7.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v7.setImageResource(R.drawable.x);
                    v7.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v8 = (ImageView)findViewById(R.id.imageView8);
        v8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v8.setImageResource(R.drawable.circulo);
                    v8.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v8.setImageResource(R.drawable.x);
                    v8.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        v9 = (ImageView)findViewById(R.id.imageView9);
        v9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jogador == true){
                    v9.setImageResource(R.drawable.circulo);
                    v9.setTag(R.drawable.circulo);
                    jogador = false;
                }else {
                    v9.setImageResource(R.drawable.x);
                    v9.setTag(R.drawable.x);
                    jogador = true;
                }
                validarJogada();
            }
        });

        iniciar();
    }
}
