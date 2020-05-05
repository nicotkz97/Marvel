package com.example.marvel.Controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvel.R;
import com.squareup.picasso.Picasso;

import static com.example.marvel.Controler.BD.E_NOM;
import static com.example.marvel.Controler.BD.E_URL;


public class DetailActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String imageURL = intent.getStringExtra(E_URL);
        String nom = intent.getStringExtra(E_NOM);


        ImageView imageView = findViewById(R.id.detail_imageView);
        TextView textView1 = findViewById(R.id.text_view_detail_nom);


        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);

        textView1.setText(nom);



    }
}
