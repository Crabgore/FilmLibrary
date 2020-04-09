package com.geekbrains.team.filmlibrary.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoItemBinding;
import com.geekbrains.team.filmlibrary.model.MovieView;
import com.geekbrains.team.filmlibrary.model.TVShowView;
import com.squareup.picasso.Picasso;

public class FullInfoAdapter extends RecyclerView.Adapter<FullInfoAdapter.ViewHolder> {
    private MovieView movie;
    private TVShowView tvShow;

    public FullInfoAdapter() {
    }

    public void setMovie(MovieView data) {
        movie = data;
    }

    public void setTvShow(TVShowView data) {
        tvShow = data;
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FullFilmInfoItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.full_film_info_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String trailer;
        String backDrop;

        if (movie != null) {
            holder.bindMovie(movie);
            trailer = movie.getTrailer();
            backDrop = movie.getImages().get(position);
        } else {
            holder.bindTVShow(tvShow);
            trailer = tvShow.getTrailer();
            backDrop = tvShow.getImages().get(position);
        }

        String finalTrailer = trailer;
        holder.play.setOnClickListener(v -> v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalTrailer))));
        Picasso.get().load(backDrop).into(holder.backDrop);
    }

    @Override
    public int getItemCount() {
        if (movie != null)
            return movie.getImages().size();
        else
            return tvShow.getImages().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton play;
        private ImageView backDrop;

        FullFilmInfoItemBinding binding;

        ViewHolder(FullFilmInfoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            play = binding.getRoot().findViewById(R.id.play_btn);
            backDrop = binding.getRoot().findViewById(R.id.title_iv);
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
