package com.niken.catalogmovie.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.niken.catalogmovie.DetailActivity;
import com.niken.catalogmovie.R;
import com.niken.catalogmovie.model.MovieModel;
import com.niken.catalogmovie.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.niken.catalogmovie.controller.AppConfig.BASE_POSTER_URL;

public class MovieAdapterCard extends RecyclerView.Adapter <MovieAdapterCard.MovieHolder> {
    List<Result> results;

    public MovieAdapterCard(List<Result> results) {
        this.results = results;
    }

    @Override
    public MovieAdapterCard.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movie, parent, false);

        return new MovieAdapterCard.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapterCard.MovieHolder holder, final int position) {

        holder.tittle.setText(results.get(position).getTitle());
        holder.time.setText((results.get(position).getReleaseDate()));
        holder.rating.setText(Double.toString(results.get(position).getVoteAverage()));


        Picasso.with(holder.itemView.getContext())
                .load(BASE_POSTER_URL+results.get(position).getPosterPath())
                .into(holder.Poster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result data = results.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("movie" , new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    public void setData(List<Result> results) {
        this.results = results;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        TextView tittle, time, rating;
        ImageView Poster;
        public MovieHolder(View itemView) {
            super(itemView);

            tittle = (TextView) itemView.findViewById(R.id.tittle);
            time = (TextView) itemView.findViewById(R.id.time);
            rating = (TextView) itemView.findViewById(R.id.rating);
            Poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
