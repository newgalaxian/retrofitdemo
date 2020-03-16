package com.csdevbin.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private RecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // mRecyclerView.setAdapter(mAdapter);
        retroCall();

    }

    private void retroCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                /*if (!response.isSuccessful()) {

                }*/
                List<Post> posts = response.body();

                mRecyclerView = findViewById(R.id.recycler);
                manager = new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setHasFixedSize(true);
                adapter = new RecyclerAdapter(posts);
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
