package up.edu.br.jogodamemoria;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    Partida partida;
    Jogador primeiroLugar;

    TextView tv_p1, tv_p2, tv_nome, tv_nome2;

    ImageView   iv_01, iv_02,
                iv_10, iv_11, iv_12, iv_13,
                iv_20, iv_21, iv_22, iv_23,
                iv_30, iv_31, iv_32, iv_33,
                iv_40, iv_41, iv_42, iv_43;

    // array das imagens
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 107, 108,
                            201, 202, 203, 204, 205, 206, 207, 208};

    //      Atual imagens
    int     image101, image102, image103, image104, image105, image106, image107, image108,
            image201, image202, image203, image204, image205, image206, image207, image208;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;

    int cardNumber = 1;
    int turn = 1;
    int playerPoints = 0, cpuPoints = 0;
    boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        primeiroLugar = new JogadorDao().primeiroDoRanking();

        iv_01 = (ImageView)findViewById(R.id.imageP1);
        iv_02 = (ImageView)findViewById(R.id.imageP2);
        tv_nome = (TextView)findViewById(R.id.tv_nome);
        tv_nome2 = (TextView)findViewById(R.id.tv_nome2);

        Intent it = getIntent();
        if(it != null && it.hasExtra("partida")) {
            partida = (Partida)it.getSerializableExtra("partida");

            ByteArrayInputStream imageStream = new ByteArrayInputStream(partida.getJogador1().getImagem());
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            iv_01.setImageBitmap(bitmap);
            tv_nome.setText(partida.getJogador1().getNome());

            imageStream = new ByteArrayInputStream(partida.getJogador2().getImagem());
            bitmap = BitmapFactory.decodeStream(imageStream);
            iv_02.setImageBitmap(bitmap);
            tv_nome2.setText(partida.getJogador2().getNome());
        }

        tv_p1 = (TextView)findViewById(R.id.tv_p1);
        tv_p2 = (TextView)findViewById(R.id.tv_p2);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_10 = (ImageView) findViewById(R.id.iv_10);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_20 = (ImageView) findViewById(R.id.iv_20);
        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        iv_30 = (ImageView) findViewById(R.id.iv_30);
        iv_41 = (ImageView) findViewById(R.id.iv_41);
        iv_42 = (ImageView) findViewById(R.id.iv_42);
        iv_43 = (ImageView) findViewById(R.id.iv_43);
        iv_40 = (ImageView) findViewById(R.id.iv_40);

        iv_10.setTag("0");
        iv_11.setTag("1");
        iv_12.setTag("2");
        iv_13.setTag("3");
        iv_20.setTag("4");
        iv_21.setTag("5");
        iv_22.setTag("6");
        iv_23.setTag("7");
        iv_30.setTag("8");
        iv_31.setTag("9");
        iv_32.setTag("10");
        iv_33.setTag("11");
        iv_40.setTag("12");
        iv_41.setTag("13");
        iv_42.setTag("14");
        iv_43.setTag("15");

        // CARREGA OS CARDS COM IMAGENS DE ACORDO COM O TEMA
        frontOfCardsResources(partida.getTema());

        // EMBARALHA OS CARDS
        Collections.shuffle(Arrays.asList(cardsArray));

        iv_11.setOnClickListener(this);
        iv_12.setOnClickListener(this);
        iv_13.setOnClickListener(this);
        iv_10.setOnClickListener(this);
        iv_21.setOnClickListener(this);
        iv_22.setOnClickListener(this);
        iv_23.setOnClickListener(this);
        iv_20.setOnClickListener(this);
        iv_31.setOnClickListener(this);
        iv_32.setOnClickListener(this);
        iv_33.setOnClickListener(this);
        iv_30.setOnClickListener(this);
        iv_41.setOnClickListener(this);
        iv_42.setOnClickListener(this);
        iv_43.setOnClickListener(this);
        iv_40.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int theCard;

        switch (view.getId()) {
            case R.id.iv_11:
                pause = true;
                Toast.makeText(this, String.valueOf(view.getId())+" VS "+ String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_11, theCard);
                break;
            case R.id.iv_12:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_12, theCard);
                break;
            case R.id.iv_13:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_13, theCard);
                break;
            case R.id.iv_10:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_10, theCard);
                break;
            case R.id.iv_21:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_21, theCard);
                break;
            case R.id.iv_22:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_22, theCard);
                break;
            case R.id.iv_23:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_23, theCard);
                break;
            case R.id.iv_20:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_20, theCard);
                break;
            case R.id.iv_31:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_31, theCard);
                break;
            case R.id.iv_32:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_32, theCard);
                break;
            case R.id.iv_33:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_33, theCard);
                break;
            case R.id.iv_30:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_30, theCard);
                break;
            case R.id.iv_41:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_41, theCard);
                break;
            case R.id.iv_42:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_42, theCard);
                break;
            case R.id.iv_43:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_43, theCard);
                break;
            case R.id.iv_40:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_40, theCard);
                break;
        }
    }

    private void doStuff(ImageView iv, int card) {

        Toast.makeText(this, String.valueOf(card), Toast.LENGTH_SHORT).show();

        // DEFINE A IMAGEM CORRETA PARA O IMAGEVIEW
        switch (cardsArray[card]) {
            case 101:
                iv.setImageResource(image101);
                break;
            case 102:
                iv.setImageResource(image102);
                break;
            case 103:
                iv.setImageResource(image103);
                break;
            case 104:
                iv.setImageResource(image104);
                break;
            case 105:
                iv.setImageResource(image105);
                break;
            case 106:
                iv.setImageResource(image106);
                break;
            case 107:
                iv.setImageResource(image107);
                break;
            case 108:
                iv.setImageResource(image108);
                break;
            case 201:
                iv.setImageResource(image201);
                break;
            case 202:
                iv.setImageResource(image202);
                break;
            case 203:
                iv.setImageResource(image203);
                break;
            case 204:
                iv.setImageResource(image204);
                break;
            case 205:
                iv.setImageResource(image205);
                break;
            case 206:
                iv.setImageResource(image206);
                break;
            case 207:
                iv.setImageResource(image207);
                break;
            case 208:
                iv.setImageResource(image208);
                break;
        }

        // VERIFICA QUAL IMAGEM ESTÁ SELECIONADA E SALVA TEMPORARIAMENTE
        if (cardNumber == 1) {

            firstCard = cardsArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);

        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_10.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_20.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_30.setEnabled(false);
            iv_41.setEnabled(false);
            iv_42.setEnabled(false);
            iv_43.setEnabled(false);
            iv_40.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    // VERIFICA SE AS IMAGENS SELECIONADAS SÃO IGUAIS
                    calculate();
                }
            }, 1000);
        }

        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // VERIFICA SE AS IMAGENS SELECIONADAS SÃO IGUAIS
                pause = false;
            }
        }, 1000);

    }

    private void calculate() {
        //SE AS IMAGENS FOREM IGUAIS, SÃO REMOVIDAS E OS ESPAÇOS FICAM VAZIOS
        if(firstCard == secondCard){
            if(clickedFirst == 0) {
                iv_10.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 1){
                iv_11.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 2){
                iv_12.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 3){
                iv_13.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 4){
                iv_20.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 5){
                iv_21.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 6){
                iv_22.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 7){
                iv_23.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 8){
                iv_30.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 9){
                iv_31.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 10) {
                iv_32.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 11) {
                iv_33.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 12){
                iv_40.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 13){
                iv_41.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 14) {
                iv_42.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 15) {
                iv_43.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0) {
                iv_10.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 1){
                iv_11.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 2){
                iv_12.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 3){
                iv_13.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 4){
                iv_20.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 5){
                iv_21.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 6){
                iv_22.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 7){
                iv_23.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 8){
                iv_30.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 9){
                iv_31.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 10) {
                iv_32.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 11) {
                iv_33.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 12){
                iv_40.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 13){
                iv_41.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 14) {
                iv_42.setVisibility(View.INVISIBLE);
            }else if (clickedSecond == 15) {
                iv_43.setVisibility(View.INVISIBLE);
            }

            // ADICIONA PONTOS PARA O JOGADOR
            if(turn == 1){
                playerPoints++;
                tv_p1.setText("P1:  "+ playerPoints);
            }else if (turn == 2){
                cpuPoints++;
                tv_p2.setText("P2:  "+ cpuPoints);
            }

        }else{
            iv_11.setImageResource(R.mipmap.ic_interrogacao);
            iv_12.setImageResource(R.mipmap.ic_interrogacao);
            iv_13.setImageResource(R.mipmap.ic_interrogacao);
            iv_10.setImageResource(R.mipmap.ic_interrogacao);
            iv_21.setImageResource(R.mipmap.ic_interrogacao);
            iv_22.setImageResource(R.mipmap.ic_interrogacao);
            iv_23.setImageResource(R.mipmap.ic_interrogacao);
            iv_20.setImageResource(R.mipmap.ic_interrogacao);
            iv_31.setImageResource(R.mipmap.ic_interrogacao);
            iv_32.setImageResource(R.mipmap.ic_interrogacao);
            iv_33.setImageResource(R.mipmap.ic_interrogacao);
            iv_30.setImageResource(R.mipmap.ic_interrogacao);
            iv_41.setImageResource(R.mipmap.ic_interrogacao);
            iv_42.setImageResource(R.mipmap.ic_interrogacao);
            iv_43.setImageResource(R.mipmap.ic_interrogacao);
            iv_40.setImageResource(R.mipmap.ic_interrogacao);


            // TROCA O TURNO DO JOGADOR
            if(turn == 1){
                turn = 2;
                tv_p1.setTextColor(Color.GRAY);
                tv_p2.setTextColor(Color.BLACK);
            }else if(turn == 2){
                turn = 1;
                tv_p1.setTextColor(Color.BLACK);
                tv_p2.setTextColor(Color.GRAY);
            }
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_10.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_20.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_30.setEnabled(true);
        iv_41.setEnabled(true);
        iv_42.setEnabled(true);
        iv_43.setEnabled(true);
        iv_40.setEnabled(true);

        // VERIFICA SE O JOGO TERMINOU
        checkEnd();
    }

    private void checkEnd() {
        if(     iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_10.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_20.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_30.getVisibility() == View.INVISIBLE &&
                iv_41.getVisibility() == View.INVISIBLE &&
                iv_42.getVisibility() == View.INVISIBLE &&
                iv_43.getVisibility() == View.INVISIBLE &&
                iv_40.getVisibility() == View.INVISIBLE){

            //CONTAR VITÓRIAS E DERROTAS
            if(playerPoints > cpuPoints && partida.getJogador1().getId() != null){
                partida.getJogador1().setVitorias(partida.getJogador1().getVitorias() + 1);
                partida.getJogador2().setDerrotas(partida.getJogador2().getDerrotas() + 1);
                new JogadorDao().vitoria(partida.getJogador1());
                new JogadorDao().derrota(partida.getJogador2());
            }
            if(cpuPoints > playerPoints && partida.getJogador1().getId() != null){
                partida.getJogador1().setDerrotas(partida.getJogador1().getDerrotas() + 1);
                partida.getJogador2().setVitorias(partida.getJogador2().getVitorias() + 1);
                new JogadorDao().vitoria(partida.getJogador2());
                new JogadorDao().derrota(partida.getJogador1());
            }

            //SE O PRIMEIRO LUGAR ATUAL FOR ULTRAPASSADO GERA UMA NOTIFICAÇÃO
            if(primeiroLugar.getId() != new JogadorDao().primeiroDoRanking().getId()){
                gerarNotificação(new JogadorDao().primeiroDoRanking());
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameActivity.this);
            alertDialogBuilder
                    .setMessage("GAME OVER! \n P1: " +partida.getJogador1().getNome()+" " + playerPoints
                                         + "\n P2: " +partida.getJogador2().getNome()+" " + cpuPoints)
                    .setCancelable(false)
                    .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), SelecionarActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    private void frontOfCardsResources(String tema) {

        switch (tema){
            case "Mário":
                image101 = R.drawable.image01;
                image102 = R.drawable.image02;
                image103 = R.drawable.image03;
                image104 = R.drawable.image04;
                image105 = R.drawable.image05;
                image106 = R.drawable.image06;
                image107 = R.drawable.image07;
                image108 = R.drawable.image08;
                image201 = R.drawable.image01;
                image202 = R.drawable.image02;
                image203 = R.drawable.image03;
                image204 = R.drawable.image04;
                image205 = R.drawable.image05;
                image206 = R.drawable.image06;
                image207 = R.drawable.image07;
                image208 = R.drawable.image08;
                break;
            case "Digimon":
                image101 = R.drawable.image09;
                image102 = R.drawable.image10;
                image103 = R.drawable.image11;
                image104 = R.drawable.image12;
                image105 = R.drawable.image13;
                image106 = R.drawable.image14;
                image107 = R.drawable.image15;
                image108 = R.drawable.image16;
                image201 = R.drawable.image09;
                image202 = R.drawable.image10;
                image203 = R.drawable.image11;
                image204 = R.drawable.image12;
                image205 = R.drawable.image13;
                image206 = R.drawable.image14;
                image207 = R.drawable.image15;
                image208 = R.drawable.image16;
                break;
            case "Looney Tunes":
                image101 = R.drawable.image33;
                image102 = R.drawable.image34;
                image103 = R.drawable.image35;
                image104 = R.drawable.image36;
                image105 = R.drawable.image37;
                image106 = R.drawable.image38;
                image107 = R.drawable.image39;
                image108 = R.drawable.image40;
                image201 = R.drawable.image33;
                image202 = R.drawable.image34;
                image203 = R.drawable.image35;
                image204 = R.drawable.image36;
                image205 = R.drawable.image37;
                image206 = R.drawable.image38;
                image207 = R.drawable.image39;
                image208 = R.drawable.image40;
                break;
            case "Naruto":
                image101 = R.drawable.image17;
                image102 = R.drawable.image18;
                image103 = R.drawable.image19;
                image104 = R.drawable.image20;
                image105 = R.drawable.image21;
                image106 = R.drawable.image22;
                image107 = R.drawable.image23;
                image108 = R.drawable.image24;
                image201 = R.drawable.image17;
                image202 = R.drawable.image18;
                image203 = R.drawable.image19;
                image204 = R.drawable.image20;
                image205 = R.drawable.image21;
                image206 = R.drawable.image22;
                image207 = R.drawable.image23;
                image208 = R.drawable.image24;
                break;
            case "Hora de Aventura":
                image101 = R.drawable.image25;
                image102 = R.drawable.image26;
                image103 = R.drawable.image27;
                image104 = R.drawable.image28;
                image105 = R.drawable.image29;
                image106 = R.drawable.image30;
                image107 = R.drawable.image31;
                image108 = R.drawable.image32;
                image201 = R.drawable.image25;
                image202 = R.drawable.image26;
                image203 = R.drawable.image27;
                image204 = R.drawable.image28;
                image205 = R.drawable.image29;
                image206 = R.drawable.image30;
                image207 = R.drawable.image31;
                image208 = R.drawable.image32;
                break;
        }
    }

    public void gerarNotificação(Jogador j){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sync_black_24dp)
                        .setContentTitle("O Primeiro lugar acaba de ser ultrapassado")
                        .setContentText("1º " + j.getNome())
                        .setAutoCancel(true);

        // Cria o intent que irá chamar a atividade a ser aberta quando clicar na notifição
        Intent resultIntent = new Intent(GameActivity.this, RankingActivity.class);

        //PendingIntent é "vinculada" a uma notification para abrir a intent
        PendingIntent resultPendingIntent = PendingIntent.
                getActivity(GameActivity.this, 0, resultIntent, 0);

        //associa o intent na notificação
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //gera a notificação
        mNotificationManager.notify(99, mBuilder.build());
    }
}
