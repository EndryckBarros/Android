package com.endryckbarros.olxapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.endryckbarros.olxapp.R;
import com.endryckbarros.olxapp.adapter.AdapterAnuncios;
import com.endryckbarros.olxapp.helper.ConfuguracaoFirebase;
import com.endryckbarros.olxapp.helper.RecyclerItemClickListener;
import com.endryckbarros.olxapp.model.Anuncio;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class AnunciosActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private RecyclerView recyclerAnunciosPublicos;
    private Button buttonRegiao, buttonCategoria, buttonLimpar;
    private AdapterAnuncios adapterAnuncios;
    private List<Anuncio> anuncios = new ArrayList<>();
    private DatabaseReference anunciosPublicosRef;
    private AlertDialog dialog;
    private String filtroEstado, filtroCategoria = "";
    private boolean filtrandoPorEstado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        inicializarComponentes();

        autenticacao = ConfuguracaoFirebase.getFirebaseAutenticacao();
        anunciosPublicosRef = ConfuguracaoFirebase.getFirebase().child("Anuncios");

        //CONFIGURAR RECYCLERVIEW
        recyclerAnunciosPublicos.setLayoutManager(new LinearLayoutManager(this));
        recyclerAnunciosPublicos.setHasFixedSize(true);
        adapterAnuncios = new AdapterAnuncios(anuncios, this);
        recyclerAnunciosPublicos.setAdapter(adapterAnuncios);

        recuperarAnunciosPublicos();

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anunciosPublicosRef = ConfuguracaoFirebase.getFirebase().child("Anuncios");
                filtrandoPorEstado = false;
                recuperarAnunciosPublicos();
            }
        });

        recyclerAnunciosPublicos.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerAnunciosPublicos,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Anuncio anuncioSelecionado = anuncios.get(position);
                Intent it = new Intent(AnunciosActivity.this, DetalhesProdutoActivity.class);
                it.putExtra("AnuncioSelecionado", anuncioSelecionado);
                startActivity(it);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

    }

    public void inicializarComponentes(){

        recyclerAnunciosPublicos = findViewById(R.id.recyclerAnunciosPublicos);
        //buttonCategoria = findViewById(R.id.buttonCategoria);
        //buttonRegiao = findViewById(R.id.buttonRegiao);
        buttonLimpar = findViewById(R.id.buttonLimpar);
    }

    public void filtrarPorEstado(View view){
        AlertDialog.Builder dialogEstado = new AlertDialog.Builder(this);
        dialogEstado.setTitle("Selecione o Estado Desejado");

        View viewSpinner = getLayoutInflater().inflate(R.layout.dialog_spinner, null);

        //CONFIGURA SPINNER ESTADOS
        final Spinner spinnerEstado = viewSpinner.findViewById(R.id.spinnerFiltro);
        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        dialogEstado.setView(viewSpinner);
        dialogEstado.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filtroEstado = spinnerEstado.getSelectedItem().toString();
                if(!filtroEstado.equals("(Estado)")){
                    recuperarAnunciosPorEstado();
                    filtrandoPorEstado = true;
                }
            }
        });

        dialogEstado.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = dialogEstado.create();
        dialog.show();
    }

    public void filtrarPorCategoria(View view){

        if(filtrandoPorEstado){
            AlertDialog.Builder dialogEstado = new AlertDialog.Builder(this);
            dialogEstado.setTitle("Selecione a Categoria Desejado");

            View viewSpinner = getLayoutInflater().inflate(R.layout.dialog_spinner, null);

            //CONFIGURA SPINNER CATEGORIAS
            final Spinner spinnerCategoria = viewSpinner.findViewById(R.id.spinnerFiltro);
            String[] estados = getResources().getStringArray(R.array.categorias);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estados);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategoria.setAdapter(adapter);

            dialogEstado.setView(viewSpinner);
            dialogEstado.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    filtroCategoria = spinnerCategoria.getSelectedItem().toString();
                    if(!filtroCategoria.equals("(Categoria)")){
                        recuperarAnunciosPorCategoria();
                    }
                }
            });

            dialogEstado.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = dialogEstado.create();
            dialog.show();
        }else{
            Toast.makeText(this, "Selecione Primeiro Sua Região!", Toast.LENGTH_SHORT).show();
        }
    }

    public void recuperarAnunciosPublicos(){
        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Carregando Anúnicios")
                .setCancelable(false)
                .build();
        dialog.show();

        anuncios.clear();
        anunciosPublicosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot estado : dataSnapshot.getChildren()){
                    for(DataSnapshot categoria : estado.getChildren()){
                        for(DataSnapshot anuncio : categoria.getChildren()){

                            Anuncio anuncioResult = anuncio.getValue(Anuncio.class);
                            anuncios.add(anuncioResult);
                        }
                    }
                }

                Collections.reverse(anuncios);
                adapterAnuncios.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void recuperarAnunciosPorEstado(){
        anunciosPublicosRef = ConfuguracaoFirebase.getFirebase().child("Anuncios").child(filtroEstado);
        anunciosPublicosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                anuncios.clear();
                for(DataSnapshot categoria : dataSnapshot.getChildren()){
                    for(DataSnapshot anuncio : categoria.getChildren()){

                        Anuncio anuncioResult = anuncio.getValue(Anuncio.class);
                        anuncios.add(anuncioResult);
                    }
                }
                Collections.reverse(anuncios);
                adapterAnuncios.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void recuperarAnunciosPorCategoria(){
        anunciosPublicosRef = ConfuguracaoFirebase.getFirebase()
                .child("Anuncios")
                .child(filtroEstado)
                .child(filtroCategoria);
        anunciosPublicosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                anuncios.clear();
                for(DataSnapshot anuncio : dataSnapshot.getChildren()){
                    Anuncio anuncioResult = anuncio.getValue(Anuncio.class);
                    anuncios.add(anuncioResult);
                }
                Collections.reverse(anuncios);
                adapterAnuncios.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //OPÇÕES MENU SUPERIOS MEDIANTE VALIDAÇÃO
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(autenticacao.getCurrentUser() == null){                   //USUARIO DESLOGADO
            menu.setGroupVisible(R.id.group_deslogado, true);
        }else {                                                      //USUARIO LOGADO
            menu.setGroupVisible(R.id.group_logado, true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_cadastrar:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.menu_sair:
                autenticacao.signOut();  //DESLOGAR USUÁRIO
                invalidateOptionsMenu(); //RECARREGAR MENU
                break;
            case R.id.menu_anuncios:
                startActivity(new Intent(getApplicationContext(), MeusAnunciosActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
