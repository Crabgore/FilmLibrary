package com.geekbrains.team.filmlibrary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.SmallCardItemBinding;
import com.geekbrains.team.filmlibrary.model.MovieView;
import com.geekbrains.team.filmlibrary.model.TVShowView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardAdapter.SmallCardHolder> {
    public List<MovieView> movie = new ArrayList<>();
    public List<TVShowView> tvShow = new ArrayList<>();

    public SmallCardAdapter() {
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    public void setMovies(List<MovieView> data) {
        movie.clear();
        movie.addAll(data);
    }

    public void setTvShows(List<TVShowView> data) {
        tvShow.clear();
        tvShow.addAll(data);
    }

    @NonNull
    @Override
    public SmallCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SmallCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.small_card_item, parent, false);
        return new SmallCardHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SmallCardHolder holder, int position) {
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

    class SmallCardHolder extends RecyclerView.ViewHolder {

        SmallCardItemBinding binding;

        SmallCardHolder(SmallCardItemBinding binding) {
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

