package esilv.a4.antoine.antoineproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {


    @GET("movie")
    Call<ResponseMovie> getMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("movie/upcoming")
    Call<ResponseMovie> getUpcoming(
            @Query("api_key") String apiKey
    );


    @GET("movie/now_playing")
    Call<ResponseMovie> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("search/movie?api_key="+ Constant.API_KEY)
    Call<ResponseMovie> getSearchMovie(
            @Query("query") String movie_name
    );
}
