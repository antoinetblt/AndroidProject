package esilv.a4.antoine.antoineproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private final MClickListener nListener;
    private List<Movie> lmovie;

    public MovieAdapter(MClickListener nListener) {
        lmovie = new ArrayList<>();
        this.nListener = nListener;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Movie setMovie = lmovie.get(position);
        holder.tvTittle.setText(setMovie.title);
        holder.tvOverview.setText(setMovie.overview);
        holder.tvRelease.setText(setMovie.title);
        //Picasso.with(holder.itemView.getContext()).load(Constant.IMAGE+"/"+setMovie.poster_path).into(holder.tvImage);

    }


    @Override
    public int getItemCount() {
        return lmovie.size();
    }

    public void addMovie(Movie movie) {
        lmovie.add(movie);
        notifyDataSetChanged();
    }

    public Movie getMenu(int position) {
        return null;
    }

    public interface MClickListener {
    }


    public class Holder extends RecyclerView.ViewHolder {
        public BreakIterator tvTittle;
        public BreakIterator tvOverview;
        public BreakIterator tvRelease;
        public Target tvImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

