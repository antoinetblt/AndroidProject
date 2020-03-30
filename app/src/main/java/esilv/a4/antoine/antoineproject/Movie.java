package esilv.a4.antoine.antoineproject;



import com.google.gson.annotations.SerializedName;


public class Movie {

    @SerializedName("vote_count")
    public int vote_count;

    @SerializedName("id")
    public int id;

    @SerializedName("video")
    public boolean video;

    @SerializedName("vote_average")
    public double vote_average;

    @SerializedName("title")
    public String title;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("adult")
    public boolean adult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String release_date;

    @SerializedName("original_language")
    public String original_language;


}
