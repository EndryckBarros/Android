package com.endryckbarros.olxapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.endryckbarros.olxapp.R;
import com.endryckbarros.olxapp.helper.Permissoes;
import com.endryckbarros.olxapp.helper.ConfuguracaoFirebase;
import com.endryckbarros.olxapp.model.Anuncio;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class CadastrarAnuncioActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText campoTitulo, campoDescricao;
    private CurrencyEditText campoValor;
    private ImageView imagem1, imagem2, imagem3;
    private Spinner campoEstado, campoCategoria;
    private String[] permissoes = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    //private List<String> listaDeFotos = new ArrayList<>();
    private String[] listaDeFotos = new String[3];
    private List<String> listaUrlFotos = new ArrayList<>();
    private StorageReference storage;
    private Anuncio anuncio;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        storage = ConfuguracaoFirebase.getFirebaseStorage();

        //VALIDAR PERMISSÕES
        Permissoes.validarPermissoes(permissoes, this, 1);

        inicializarComponentes();
        carregarDadosSpinner();
    }

    public void salvarAnuncio(){

        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Salvando Anùnicio")
                .setCancelable(false)
                .build();
                dialog.show();

        int tamanhoDaList = 0;
        for (int y=0; y < listaDeFotos.length; y++){
            if (listaDeFotos[y] != null || listaDeFotos[y].equals("")){
                tamanhoDaList++;
            }
        }

        for(int i=0; i < tamanhoDaList; i++){
            String urlImagem = listaDeFotos[i];
            int tamanhoDaLista = tamanhoDaList;
            salvarFotoStorage(urlImagem, tamanhoDaLista, i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageCadastro1:
                escolherImagem(1);
                break;
            case R.id.imageCadastro2:
                escolherImagem(2);
                break;
            case R.id.imageCadastro3:
                escolherImagem(3);
                break;
        }
    }

    public void escolherImagem(int requestCode){
        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(it, requestCode);
    }

    private void inicializarComponentes(){
        campoTitulo = findViewById(R.id.editTitulo);
        campoDescricao = findViewById(R.id.editDescricao);
        campoValor = findViewById(R.id.editValor);
        campoEstado = findViewById(R.id.spinnerEstado);
        campoCategoria = findViewById(R.id.spinnerCategoria);
        imagem1 = findViewById(R.id.imageCadastro1);
        imagem2 = findViewById(R.id.imageCadastro2);
        imagem3 = findViewById(R.id.imageCadastro3);

        //A PRÓPRIA CLASSE GERENCIA O EFEITO DE CLIQUE
        imagem1.setOnClickListener(this);
        imagem2.setOnClickListener(this);
        imagem3.setOnClickListener(this);

    }

    private void carregarDadosSpinner(){
        //String[] estados = new String[]{"PR","SP"};

        //CONFIGURA SPINNER ESTADOS
        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoEstado.setAdapter(adapter);

        //CONFIGURA SPINNER CATEGORIAS
        String[] categorias = getResources().getStringArray(R.array.categorias);
        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoCategoria.setAdapter(adapterCat);
    }

    public void validasDadosAnuncio(View view){
        contruirAnuncio();
        String valor = String.valueOf(campoValor.getRawValue());

        if(listaDeFotos[0] != null || listaDeFotos[1] != null || listaDeFotos[2] != null){
            if(!anuncio.getEstado().equals("(Estado)")) {
                if(!anuncio.getCategoria().equals("(Categoria)")) {
                    if (!anuncio.getTitulo().isEmpty()) {
                        if (!anuncio.getDescricao().isEmpty()) {
                            if (!valor.equals("0")) {

                                salvarAnuncio();

                            }else {
                                exibirMensagemErro("Informe o Valor do Anúncio!");
                            }
                        }else {
                            exibirMensagemErro("Informe a Descricao do Anúncio!");
                        }
                    }else {
                        exibirMensagemErro("Informe o Título do Anúncio!");
                    }
                }else{
                    exibirMensagemErro("Selecione a Categoria do Anúncio!");
                }
            }else{
                exibirMensagemErro("Selecione o Estado do Anúncio!");
            }
        }else{
            exibirMensagemErro("Selecione ao Menos Uma Foto!");
        }
    }

    public Anuncio contruirAnuncio(){

        anuncio = new Anuncio();
        anuncio.setEstado(campoEstado.getSelectedItem().toString());
        anuncio.setCategoria(campoCategoria.getSelectedItem().toString());
        anuncio.setTitulo(campoTitulo.getText().toString());
        anuncio.setDescricao(campoDescricao.getText().toString());
        anuncio.setValor(campoValor.getText().toString());

        return anuncio;
    }

    private void salvarFotoStorage(String urlString, final int totalFotos, int contador){
        StorageReference imgemAnuncio = storage.child("Imagens")
                .child("Anuncios").child(anuncio.getIdAnuncio()).child("imagem" + contador);

        UploadTask uploadTask = imgemAnuncio.putFile(Uri.parse(urlString));
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri firebaseUrl = taskSnapshot.getDownloadUrl();
                String urlConvertida = firebaseUrl.toString();
                listaUrlFotos.add(urlConvertida);

                if(totalFotos == listaUrlFotos.size()){
                    anuncio.setFotos(listaUrlFotos);
                    anuncio.salvarAnuncio();
                    dialog.dismiss();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                exibirMensagemErro("Falha ao Tentar Upload!");
            }
        });
    }

    private void exibirMensagemErro(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){

            //RECUPERAR IMAGEM
            Uri imagemSelecionada = data.getData();
            String caminhoImagem = imagemSelecionada.toString();

            //CONFIGURAR IMAGEVIEW'S
            if(requestCode == 1){
                imagem1.setImageURI(imagemSelecionada);
                listaDeFotos[0] = caminhoImagem;
            }else if(requestCode == 2){
                imagem2.setImageURI(imagemSelecionada);
                listaDeFotos[1] = caminhoImagem;
            }else if(requestCode == 3){
                imagem3.setImageURI(imagemSelecionada);
                listaDeFotos[2] = caminhoImagem;
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int permissaoResultado : grantResults){
            if (permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertValidacaoPermissao();
            }
        }
    }

    private void alertValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para Utilizar o APP, é necessário ACEITAR as permissões!");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
