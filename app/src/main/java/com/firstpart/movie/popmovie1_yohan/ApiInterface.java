package com.firstpart.movie.popmovie1_yohan;

/**
 * Created by Yohan on 02/05/2018.
 */

import com.firstpart.movie.popmovie1_yohan.ReviewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailerResponse> getVideos(@Path("id") String id, @Query("api_key") String apiKey);







}


