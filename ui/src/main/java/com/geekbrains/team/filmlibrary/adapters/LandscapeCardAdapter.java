package com.geekbrains.team.filmlibrary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.LandscapeCardItemBinding;
import com.geekbrains.team.filmlibrary.model.MovieView;
import com.geekbrains.team.filmlibrary.model.TVShowView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class LandscapeCardAdapter extends RecyclerView.Adapter<LandscapeCardAdapter.LandscapeCardHolder> {
    private List<MovieView> movie = new ArrayList<>();
    private List<TVShowView> tvShow = new ArrayList<>();

    public LandscapeCardAdapter() {
    }

    public void setMovies(List<MovieView> data) {
        movie.clear();
        movie.addAll(data);
    }

    public void setTVShows(List<TVShowView> data) {
        tvShow.clear();
        tvShow.addAll(data);
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
        if (!movie.isEmpty())
            holder.bindMovie(movie.get(position));
        else
            holder.bindTVShow(tvShow.get(position));
    }

    @Override
    public int getItemCount() {
        if (!movie.isEmpty())
            return movie.size();
        else
            return tvShow.size();
    }

    class LandscapeCardHolder extends RecyclerView.ViewHolder {

        LandscapeCardItemBinding binding;

        LandscapeCardHolder(LandscapeCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindMovie(MovieView movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        void bindTVShow(TVShowView movie) {
            binding.setTvShow(movie);
            binding.executePendingBindings();
        }
    }
}