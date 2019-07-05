package com.endryckbarros.olxapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.endryckbarros.olxapp.R;
import com.endryckbarros.olxapp.helper.ConfuguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private Button botaoAcessar;
    private EditText campoEmail, campoSenha;
    private Switch tipoAcesso;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes();
        autenticacao = ConfuguracaoFirebase.getFirebaseAutenticacao();

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if(!email.isEmpty()){
                    if(!senha.isEmpty()){
                        if(tipoAcesso.isChecked()){
                            autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this, "Cadastro Realizado com Sucesso!", Toast.LENGTH_SHORT).show();

                                        // DIRECIONAR USUÁRIO PARA TELA PRINCIPAL

                                    }else{
                                        String erroExcecao = "";
                                        try{
                                            throw task.getException();
                                        }catch (FirebaseAuthWeakPasswordException e){
                                            erroExcecao = "Digite Uma Senha Mais Forte!";
                                        }catch (FirebaseAuthInvalidCredentialsException e){
                                            erroExcecao = "Por Favor, Digite Um Email Válido!";
                                        }catch (FirebaseAuthUserCollisionException e){
                                            erroExcecao = "Esta Conta Já Esta Cadastrada!";
                                        }catch (Exception e) {
                                            erroExcecao = "ao Cadastrar Usuario: " + e.getMessage();
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(LoginActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else {

                            autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this, "Logado Com Sucesso!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), AnunciosActivity.class));
                                    }else{
                                        //Toast.makeText(LoginActivity.this, "Erro ao Fazer Login: " + task.getException(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(LoginActivity.this, "Email ou Senha Incorretos", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Preencha o Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializarComponentes(){
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        tipoAcesso = findViewById(R.id.switchAcesso);
        botaoAcessar = findViewById(R.id.buttonAcesso);
    }
}
