package com.example.marvel.Controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marvel.Controler.BD;
import com.example.marvel.Controler.Film;
import com.example.marvel.R;


public class MainActivity extends AppCompatActivity {


    private Button film,bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        film = findViewById(R.id.film);
        bd = findViewById(R.id.bd);

        film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent (getApplicationContext(), Film.class);
                startActivity(i);
                finish();
            }
        });

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i2 = new Intent (getApplicationContext(), BD.class);
                startActivity(i2);
                finish();
            }
        });



    }



}
