package up.edu.br.contatos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ContatoActivity extends AppCompatActivity {


    Contato contato;
    private String userChoosenTask;
    int CAMERA_REQUEST = 1888, SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Spinner spTipo = (Spinner)findViewById(R.id.spTipo);

        EditText txtNome = (EditText)findViewById(R.id.txtNome);
        EditText txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        ImageView image = (ImageView)findViewById(R.id.imageView2);

        Intent it = getIntent();
        if(it != null && it.hasExtra("contato")) {
            contato = (Contato)it.getSerializableExtra("contato");

            txtNome.setText(contato.getNome());
            txtTelefone.setText(contato.getNumero());

            spTipo.setSelection(((ArrayAdapter)spTipo.getAdapter()).getPosition(contato.getTipo()));
            ByteArrayInputStream imageStream = new ByteArrayInputStream(contato.getImagem());
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
            EditText txtNome = (EditText)findViewById(R.id.txtNome);
            EditText txtNumero = (EditText)findViewById(R.id.txtTelefone);

            Spinner spTipo = (Spinner)findViewById(R.id.spTipo);
            ImageView image = (ImageView)findViewById(R.id.imageView2);



            if(contato == null){
                contato = new Contato();
            }

            contato.setNome(txtNome.getText().toString());
            contato.setNumero(txtNumero.getText().toString());
            contato.setTipo(spTipo.getSelectedItem().toString());

            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] imageInByte = baos.toByteArray();
            contato.setImagem(imageInByte);

            new ContatoDao().salvar(contato);
            contato = null;

            Toast.makeText(getApplicationContext(), "Salvo!",Toast.LENGTH_LONG).show();

            Intent it = new Intent(ContatoActivity.this,MainActivity.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void intentGaleria(){
        Intent it = new Intent();
        it.setType("image/*");
        it.setAction(it.ACTION_GET_CONTENT);
        startActivityForResult(it.createChooser(it, "Select File"), SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView image = (ImageView)findViewById(R.id.imageView2);
            image.setImageBitmap(photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
