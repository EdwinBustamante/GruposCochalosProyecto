package com.edwinbustamante.gruposcochalos.FullImage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.edwinbustamante.gruposcochalos.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class FullImageActivity extends AppCompatActivity {

    PhotoViewAttacher mAttacher;
    Button atras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_image);


        Intent i = getIntent();
        int imgPortada = i.getExtras().getInt("foto");///recibiendo la imagen del fragmente anterior
        ImageView imageView = (ImageView) findViewById(R.id.imageViewFull);
        imageView.setImageResource(imgPortada);

        //hace que la imagen sea expansible
        mAttacher = new PhotoViewAttacher(imageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_full_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardar:

                return true;

            case R.id.compartir:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void descaragarFoto(View view) {
        Toast.makeText(this, "descargar foto", Toast.LENGTH_SHORT).show();
    }
    public  void    compartiExternamente(View view){
        Toast.makeText(this, "COMPARIR EXTERNAMENTE ", Toast.LENGTH_SHORT).show();
    }

}
