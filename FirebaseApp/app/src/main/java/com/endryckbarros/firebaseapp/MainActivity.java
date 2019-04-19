package com.endryckbarros.firebaseapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DatabaseReference usuarios = reference.child("Usuario");
        Usuario usuario = new Usuario();
        usuario.setNome("Gabriela");
        usuario.setSobrenome("Silva");
        usuario.setIdade(14);

        usuarios.push().setValue(usuario);*/


        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CONFIGURA PARA IMAGEM SER SALVA EM MEMÓRIA
                /*imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);
                byte[] data = baos.toByteArray();*/

                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("Imagens"); //Ponto Child para nova pasta
                //String nomeArquivo = UUID.randomUUID().toString(); Nome Aleatório
                StorageReference imagemRef = imagens.child("Salinha.jpeg");

                //METODO UPLOAD IMAGEM
                //RETORNA OBJETO QUE IRÁ CONTROLAR O UPLOAD
                /*UploadTask uploadTask = imagemRef.putBytes(data);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( MainActivity.this, "Upload Falhou!", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText( MainActivity.this, "Upload Com Sucesso!", Toast.LENGTH_LONG).show();
                    }
                });*/

                //METODO DELETAR IMAGEM
                /*imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( MainActivity.this, "Delete Falhou!", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText( MainActivity.this, "Delete Com Sucesso!", Toast.LENGTH_LONG).show();
                    }
                });*/

                //METODO DOWNLOAD IMAGEM
                imagemRef.getDownloadUrl().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( MainActivity.this, "Loading Falhou!", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String link = uri.toString();
                        Glide.with(MainActivity.this).load(link).into(imageView);
                        Toast.makeText( MainActivity.this, "Loading Com Sucesso!", Toast.LENGTH_LONG).show();
                        //Uri url = taskSnapshot.getStorage().getDownloadUrl().
                    }
                });


            }
        });



    }
}
