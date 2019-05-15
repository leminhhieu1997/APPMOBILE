package com.example.week5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallesPelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);

        EditText titulo = findViewById(R.id.tbTituloDescripcion);
        EditText detalles = findViewById(R.id.tvdescripcion);
        ImageView img = findViewById(R.id.ivImagen);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            titulo.setText(b.getString("TIT"));
            detalles.setText(b.getString("DET"));
            img.setImageResource(b.getInt("IMG"));
        }
    }
}