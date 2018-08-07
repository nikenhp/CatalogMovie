package com.niken.catalogmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.niken.catalogmovie.controller.AppConfig;
import com.niken.catalogmovie.model.Result;
import com.squareup.picasso.Picasso;

import static com.niken.catalogmovie.controller.AppConfig.BASE_BACKDROP_URL;
import static com.niken.catalogmovie.controller.AppConfig.BASE_POSTER_URL;

public class DetailActivity extends AppCompatActivity {

    ImageView Poster, backdrop;
    TextView Tittle, Time, Overview, Rating, language;
    Toolbar toolbar;

    Result results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdrop = (ImageView) findViewById(R.id.backdrop);
        Poster = (ImageView)  findViewById(R.id.poster);
        Tittle = (TextView) findViewById(R.id.tittle);
        Time = (TextView) findViewById(R.id.time);
        Overview = (TextView) findViewById(R.id.overview);
        Rating = (TextView) findViewById(R.id.rating);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        language = (TextView) findViewById(R.id.language);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        results = new GsonBuilder()
                .create()
                .fromJson(getIntent().getStringExtra("movie"), Result.class);

        Picasso.with(DetailActivity.this)
                .load(BASE_POSTER_URL+results.getPosterPath())
                .into(Poster);
        Picasso.with(DetailActivity.this)
                .load(BASE_BACKDROP_URL+results.getBackdropPath())
                .into(backdrop);

        Tittle.setText(results.getTitle());
        Time.setText(results.getReleaseDate());
        Overview.setText(results.getOverview());
        Rating.setText(Double.toString(results.getVoteAverage()));
        language.setText(results.getOriginalLanguage());
    }
}
