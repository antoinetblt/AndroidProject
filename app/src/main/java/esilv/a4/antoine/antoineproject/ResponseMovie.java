package esilv.a4.antoine.antoineproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMovie {

    @SerializedName("page")
    public int page;

    @SerializedName("total_results")
    public  int total_results;

    @SerializedName("total_pages")
    public int total_pages;

    @SerializedName("results")
    public List<Movie> results;
}
