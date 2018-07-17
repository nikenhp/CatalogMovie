package com.niken.catalogmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.niken.catalogmovie.adapter.MovieAdapterCard;
import com.niken.catalogmovie.controller.AppConfig;
import com.niken.catalogmovie.model.MovieModel;
import com.niken.catalogmovie.model.Result;
import com.niken.catalogmovie.rest.APICall;
import com.niken.catalogmovie.rest.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.niken.catalogmovie.controller.AppConfig.API_KEY;

public class SearchActivity extends AppCompatActivity {

    RecyclerView rvSearch;
    MovieAdapterCard adapter;
    List<Result> results;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvSearch = (RecyclerView) findViewById(R.id.rvsearch);
        toolbar = (Toolbar) findViewById(R.id.toolbar_search) ;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager llmData = new LinearLayoutManager(SearchActivity.this.getApplicationContext());
        rvSearch.setLayoutManager(llmData);

        if(getIntent() != null){
            String key = getIntent().getStringExtra(AppConfig.INTENT_SEARCH);
            searchMovies(key);
        }
    }

    private void searchMovies(final String key){
        APICall api = APIClient.getRetrofit().create(APICall.class);
        Call<MovieModel> call = api.getSearch(API_KEY, key);

        results = new ArrayList<>();

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                MovieModel movie = response.body();
                adapter = new MovieAdapterCard(results);
                adapter.setData(movie.getResults());
                rvSearch.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Something went wrong"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
