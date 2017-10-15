package com.example.akshay.retrofitsample.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akshay.retrofitsample.Adapter.MoviesAdapter;
import com.example.akshay.retrofitsample.Api.ApiClient;
import com.example.akshay.retrofitsample.Api.ApiInterface;
import com.example.akshay.retrofitsample.Model.Example;
import com.example.akshay.retrofitsample.Model.Genre;
import com.example.akshay.retrofitsample.Model.Movie;
import com.example.akshay.retrofitsample.Model.MoviesResponse;
import com.example.akshay.retrofitsample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieID extends AppCompatActivity {

    private TextView id;
    private TextView title;
    private ImageView img;
    private Context context;

    private static final String TAG = MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "c031081641056bf9fe906409b8730cde";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);
        context=MovieID.this;
        id= (TextView) findViewById(R.id.text1);
        title= (TextView) findViewById(R.id.text2);
        img= (ImageView) findViewById(R.id.image);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<Example> calls = apiService.getMovieDetails(550,API_KEY);
        calls.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                int statusCode = response.code();
                Example movies = response.body();
                id.setText(movies.getId().toString());
                title.setText(movies.getTitle());
                Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+movies.getPosterPath()).into(img);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();

            }


        });


//        Call<Example> calls = apiService.getMovieDetails(550,API_KEY);
//        calls.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                int statusCode = response.code();
//                List<Genre> movies = response.body().getGenres();
//                Genre firstmovie=movies.get(0);
//                id.setText(firstmovie.getId().toString());
//                title.setText(firstmovie.getName());
//       }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                t.printStackTrace();
//
//            }
//
//
//        });
    }
}
