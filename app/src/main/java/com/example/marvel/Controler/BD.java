package com.example.marvel.Controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marvel.Model.ExampleAdapter;
import com.example.marvel.Model.ExampleItem;
import com.example.marvel.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BD extends AppCompatActivity implements ExampleAdapter.onClickItemListener {

    public static final String E_URL = "url";
    public static final String E_NOM = "nom";

    private RecyclerView recyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_d);


        recyclerView = findViewById(R.id.bd_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJson();



    }

    private void parseJson() {

        String URL = "https://raw.githubusercontent.com/nicotkz97/Marvel/master/app/src/main/java/com/example/marvel/bd.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray =response.getJSONArray("hits");

                    for(int i = 0 ;i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String nom = jsonObject.getString("nom");
                        String url = jsonObject.getString("url");

                        mExampleList.add(new ExampleItem(nom,url));

                    }
                    mExampleAdapter = new ExampleAdapter(BD.this,mExampleList);
                    recyclerView.setAdapter(mExampleAdapter);

                    mExampleAdapter.setOnItemClickListener(BD.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);




    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this,DetailActivity.class);
        ExampleItem item = mExampleList.get(position);

        i.putExtra(E_URL,item.getUrl());
        i.putExtra(E_NOM,item.getNom());

        startActivity(i);




    }
}
