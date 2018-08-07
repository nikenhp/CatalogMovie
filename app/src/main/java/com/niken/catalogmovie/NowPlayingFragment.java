package com.niken.catalogmovie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niken.catalogmovie.adapter.MovieAdapterCard;
import com.niken.catalogmovie.model.MovieModel;
import com.niken.catalogmovie.model.Result;
import com.niken.catalogmovie.rest.APICall;
import com.niken.catalogmovie.rest.APIClient;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.niken.catalogmovie.controller.AppConfig.API_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    RecyclerView mView;
    MovieAdapterCard adapter;
    List<Result> results;

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);
        mView = (RecyclerView) rootView.findViewById(R.id.rv_category);

        LinearLayoutManager llmData = new LinearLayoutManager(getActivity().getApplicationContext());
        mView.setLayoutManager(llmData);

        nowPlayingMovie();

        return rootView;
    }

    private void nowPlayingMovie () {
        String language = "id-ID";
        if (Locale.getDefault().getLanguage().equals("en"))
            language = "en-US";
        APICall api = APIClient.getRetrofit().create(APICall.class);
        Call<MovieModel> call = api.getNowPlaying(API_KEY, language);

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                MovieModel movie = response.body();
                adapter = new MovieAdapterCard(results);
                adapter.setData(movie.getResults());
                mView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }
}
