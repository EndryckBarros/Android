package calculadora.up.edu.br.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int operacao;
    Double valor1;
    boolean ponto;
    boolean porcentagem;
    Double valorPorc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView resultado = (TextView)findViewById(R.id.resultado);

        final Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("1");
                                    }
                                }
        );

        final Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("2");
                                    }
                                }
        );

        final Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("3");
                                    }
                                }
        );

        final Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("4");
                                    }
                                }
        );

        final Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("5");
                                    }
                                }
        );

        final Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("6");
                                    }
                                }
        );

        final Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("7");
                                    }
                                }
        );

        final Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("8");
                                    }
                                }
        );

        final Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("9");
                                    }
                                }
        );

        final Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(0 == Double.valueOf(resultado.getText().toString()) && !ponto){
                                            resultado.setText("");
                                        }
                                        resultado.append("0");
                                    }
                                }
        );

        Button btnMais = (Button)findViewById(R.id.btnMais);
        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.valueOf(resultado.getText().toString());
                resultado.setText("0");
                operacao =1;
                ponto = false;
            }
        });

        Button btnMenos = (Button)findViewById(R.id.btnMenos);
        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.valueOf(resultado.getText().toString());
                resultado.setText("0");
                operacao = 2;
                ponto = false;
            }
        });

        Button btnDividir = (Button)findViewById(R.id.btnDividir);
        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.valueOf(resultado.getText().toString());
                resultado.setText("0");
                operacao =3;
                ponto = false;
            }
        });

        Button btnVezes = (Button)findViewById(R.id.btnVezes);
        btnVezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.valueOf(resultado.getText().toString());
                resultado.setText("0");
                operacao =4;
                ponto = false;
            }
        });

        Button btnPot = (Button)findViewById(R.id.btnPot);
        btnPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.valueOf(resultado.getText().toString());
                resultado.setText("0");
                operacao =5;
                ponto = false;

            }
        });

        Button btnResult = (Button)findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double valor2;
                if(porcentagem == false){
                    valor2 = Double.valueOf(resultado.getText().toString());
                }else{
                    valor2 = valorPorc;
                }
                Double resultadoOperacao = 0.0;

                switch (operacao) {
                    case 1:
                        if(porcentagem == true){
                            Double conta = valor1 * valor2;
                            Double conta2 = conta/100;
                            resultadoOperacao = valor1 + conta2;
                            resultado.setText(resultadoOperacao.toString());
                            ponto = false;
                            porcentagem = false;
                        }else {
                            resultadoOperacao = valor1 + valor2;
                            resultado.setText(resultadoOperacao.toString());
                            ponto = false;
                        }
                        break;
                    case 2:
                        if(porcentagem == true){
                            Double conta = valor1 * valor2;
                            Double conta2 = conta/100;
                            resultadoOperacao = valor1 - conta2;
                            resultado.setText(resultadoOperacao.toString());
                            ponto = false;
                            porcentagem = false;
                        }else{
                            resultadoOperacao = valor1 - valor2;
                            resultado.setText(resultadoOperacao.toString());
                            ponto = false;
                        }
                        break;
                    case 3:
                        if(valor2 == 0){
                            resultado.setText("Não é possivel dividir por zero!");
                            ponto = false;
                        }else {
                            resultadoOperacao = valor1 / valor2;
                            resultado.setText(resultadoOperacao.toString());
                            ponto = false;
                        }
                        break;
                    case 4:
                        resultadoOperacao = valor1 * valor2;
                        resultado.setText(resultadoOperacao.toString());
                        ponto = false;
                        break;
                    case 5:
                        resultadoOperacao = Math.pow(valor1,valor2);
                        resultado.setText(resultadoOperacao.toString());
                        ponto = false;
                        break;
                }
            }
        });

        Button btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText("0");
                ponto = false;
            }
        });

        Button btnVirgula = (Button)findViewById(R.id.btnVirgula);
        btnVirgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ponto){
                    resultado.append(".");
                    ponto = true;
                }
            }
        });

        Button btnRaiz = (Button)findViewById(R.id.btnRaiz);
        btnRaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText(String.valueOf(Math.sqrt(Double.valueOf(resultado.getText().toString()))));
                ;

            }
        });

        Button btnPorc = (Button)findViewById(R.id.btnPorc);
        btnPorc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                porcentagem = true;
                valorPorc = Double.valueOf(resultado.getText().toString());
                resultado.append("%");
            }
        });


    }
}
