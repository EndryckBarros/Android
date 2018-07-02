package up.edu.br.jogodamemoria;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class JogadorActivity extends AppCompatActivity {

    Jogador jogador;
    int CAMERA_REQUEST = 1888;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView image = (ImageView)findViewById(R.id.imageUser);
            image.setImageBitmap(photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);

        EditText txtNome = (EditText)findViewById(R.id.txtNome);
        ImageView image = (ImageView)findViewById(R.id.imageUser);

        Intent it = getIntent();
        if(it != null && it.hasExtra("jogador")) {
            jogador = (Jogador)it.getSerializableExtra("jogador");

            txtNome.setText(jogador.getNome());

            ByteArrayInputStream imageStream = new ByteArrayInputStream(jogador.getImagem());
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            image.setImageBitmap(bitmap);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtNome = (EditText)findViewById(R.id.txtNome);
                ImageView image = (ImageView)findViewById(R.id.imageUser);

                if(jogador == null){
                    jogador = new Jogador();
                }

                jogador.setNome(txtNome.getText().toString());

                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] imageInByte = baos.toByteArray();
                jogador.setImagem(imageInByte);

                new JogadorDao().salvar(jogador);
                jogador = null;

                Toast.makeText(getApplicationContext(), "Salvo!",Toast.LENGTH_LONG).show();

                Intent it = new Intent(JogadorActivity.this,RankingActivity.class);
                startActivity(it);
            }
        });
    }
}
