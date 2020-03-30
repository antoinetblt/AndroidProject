package esilv.a4.antoine.antoineproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
MovieAdapter.MClickListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout  swipeRefreshLayout;

    @BindView(R.id.llContainer)
    LinearLayout llContainer;

    @BindView(R.id.noData)
    ImageView noData;

    @BindView(R.id.etSearch)
    EditText etSearch;

    private MovieAdapter movieAdapter;
    private ResponseMovie responseMovie;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeRefreshLayout();
        SearchMovie();
    }


    private void swipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimary));
        //swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                MainActivity.this.ShowMovie();
            }
        });


    }

    private void SearchMovie() {
        etSearch.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ShowMovie();
            }
        }));
    }

    public void OnRefresh(){
        ShowMovie();
    }





    private void ShowMovie() {
        configRecyclerView();
         MovieApi movieApi = NetworkUtils.getRetrofit().create(MovieApi.class);
        Call<ResponseMovie> call = movieApi.getMovie(Constant.API_KEY, Constant.LANGUAGE, etSearch.getText().toString());
        call.enqueue(new Callback<ResponseMovie>(){
            @Override
            public void onResponse(@NonNull Call<ResponseMovie> call, @NonNull final Response<ResponseMovie> rs) {
                if(rs.isSuccessful()){
                    responseMovie = rs.body();

                    if(rs.body().total_results != 0) {
                        for(int i = 0; i < responseMovie.results.size(); i++) {
                            Movie movie = responseMovie.results.get(i);
                            movieAdapter.addMovie(movie);
                        }
                        llContainer.setVisibility(View.VISIBLE);
                        noData.setVisibility(View.GONE);

                    }
                    else{
                        llContainer.setVisibility(View.GONE);
                        noData.setVisibility(View.VISIBLE);
                    }

                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            public void onFailure(@NonNull Call<ResponseMovie> call, @NonNull Throwable t) {
                Log.e("error ", String.valueOf(t.getMessage()));
            }

        });

    }

    public void configRecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool( new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);

    }


    //@Override
    public void onClick(int position) {
        movie = movieAdapter.getMenu(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("vote_count", movie.vote_count);
        intent.putExtra("vote_average", movie.vote_average);
        intent.putExtra("title", movie.title);
        intent.putExtra("popularity", movie.popularity);
        intent.putExtra("poster_path", movie.poster_path);
        intent.putExtra("original_language", movie.original_language);
        intent.putExtra("overview", movie.overview);
        intent.putExtra("overview", movie.release_date);

        startActivity(intent);




    }




}
