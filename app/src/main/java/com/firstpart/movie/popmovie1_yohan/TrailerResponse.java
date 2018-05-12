package com.firstpart.movie.popmovie1_yohan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yohan on 03/05/2018.
 */

public class TrailerResponse {
    @SerializedName("id")
    private  int id;
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Trailer> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
