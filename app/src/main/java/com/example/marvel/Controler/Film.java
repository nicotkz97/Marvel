package com.example.marvel.Controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.marvel.Model.ListMarvelAdapter;
import com.example.marvel.Model.Marvel;
import com.example.marvel.Model.MarvelAPI;
import com.example.marvel.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Film extends AppCompatActivity {


    private final String BASE_URL = "https://raw.githubusercontent.com/Raffael93/Marvel/master/app/src/main/java/com/example/marvel/";


    private RecyclerView recyclerView;
    private ListMarvelAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private SharedPreferences s;
    private Gson gson;
    private List<Marvel> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        gson = new GsonBuilder().setLenient().create();
        s = getSharedPreferences("Marvel ", Context.MODE_PRIVATE);


        List<Marvel> liste = cache();
        if(liste != null){
            showList(liste);
        }else{
            makeApiCall();
        }

    }

    private void showList(List<Marvel> listeMarvel) {
        recyclerView = findViewById(R.id.marvel_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListMarvelAdapter(listeMarvel,getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MarvelAPI clubApi = retrofit.create(MarvelAPI.class);

        Call<List<Marvel>> call = clubApi.getMarvel();
        call.enqueue(new Callback<List<Marvel>>() {
            @Override
            public void onResponse(Call<List<Marvel>> call, Response<List<Marvel>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    saveList(list);
                    showList(list);
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();

                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Marvel>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Marvel> list){

        String jsonString  = gson.toJson(list);

        s.edit().putString("jsonString",jsonString).apply();


    }
    private List<Marvel> cache(){

        String jsonMarvel = s.getString("jsonString",null);
        if(jsonMarvel == null){
            return null;
        }else{
            Type listeType = new TypeToken<List<Marvel>>(){}.getType();
            return gson.fromJson(jsonMarvel,listeType);
        }


    }






    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}