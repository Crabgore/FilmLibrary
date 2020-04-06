package com.geekbrains.team.filmlibrary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.domain.movies.model.Movie;
import com.geekbrains.team.domain.tv.model.TVShow;
import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.LandscapeCardItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class MovieLandscapeCardAdapter extends RecyclerView.Adapter<MovieLandscapeCardAdapter.LandscapeCardHolder> {
    private List<Movie> movies = new ArrayList<>();
    private List<TVShow> tvShows = new ArrayList<>();

    public MovieLandscapeCardAdapter() {
    }

    public void setMovies(List<Movie> data) {
        movies.clear();
        movies.addAll(data);
    }

    public void setTvShows(List<TVShow> data) {
        tvShows.clear();
        tvShows.addAll(data);
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @NonNull
    @Override
    public LandscapeCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LandscapeCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.landscape_card_item, parent, false);
        return new LandscapeCardHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LandscapeCardHolder holder, int position) {
        if (!movies.isEmpty())
            holder.bindMovie(movies.get(position));
        else
            holder.bindTVShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class LandscapeCardHolder extends RecyclerView.ViewHolder {

        LandscapeCardItemBinding binding;

        LandscapeCardHolder(LandscapeCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindMovie(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        void bindTVShow(TVShow tvShow) {
            binding.setTvShow(tvShow);
            binding.executePendingBindings();
        }
    }
}