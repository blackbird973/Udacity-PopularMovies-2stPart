package com.firstpart.movie.popmovie1_yohan;


import android.content.ContentValues;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.List;

import android.graphics.Typeface;

import android.util.Log;
import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by Yohan on 28/03/2018.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    //For test
    private final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();

    //AJOUT REVIEWS
    private final static String API_KEY = "7a5b337ae2d5f9921497cb1ef4a13dc1";

    private Menu mMenu;

    private TaskDbHelper mHelper;
    private ContentValues cv = new ContentValues();
    private Cursor c;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mHelper = new TaskDbHelper(this);


        final TextView tvOriginalTitle = (TextView) findViewById(R.id.textview_original_title);
        final ImageView ivPoster = (ImageView) findViewById(R.id.imageview_poster);
        final TextView tvOverView = (TextView) findViewById(R.id.textview_overview);
        final TextView tvVoteAverage = (TextView) findViewById(R.id.textview_vote_average);
        final TextView tvReleaseDate = (TextView) findViewById(R.id.textview_release_date);
        final TextView tvId = (TextView) findViewById(R.id.tv_id);

        Intent intent = getIntent();
        final Button fav_button = findViewById(R.id.fav_button);



        //ADD FAVS OnClick
        final Movie movie = intent.getParcelableExtra(getString(R.string.parcel_movie));
        tvId.setText(movie.getId());
        tvOriginalTitle.setText(movie.getOriginalTitle());


        c = getContentResolver().query(FavoriteContract.MovieEntry.CONTENT_URI, null,
                FavoriteContract.MovieEntry.COLUMN_MOVIE_ID + " = " +
                        DatabaseUtils.sqlEscapeString(movie.getId()), null, null);

        if(c.getCount() != 0){

            fav_button.setVisibility(View.GONE);
        }


        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cv.put(FavoriteContract.MovieEntry.COLUMN_MOVIE_ID, movie.getId());
                cv.put(FavoriteContract.MovieEntry.COLUMN_TITLE, movie.getOriginalTitle());
                cv.put(FavoriteContract.MovieEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
                cv.put(FavoriteContract.MovieEntry.COLUMN_AVG_VOTE, movie.getDetailedVoteAverage());
                cv.put(FavoriteContract.MovieEntry.COLUMN_DESCRIP, movie.getOverview());
                cv.put(FavoriteContract.MovieEntry.COLUMN_REL_DATE, movie.getReleaseDate());
                getContentResolver().insert(FavoriteContract.MovieEntry.CONTENT_URI, cv);


                 fav_button.setVisibility(View.GONE);





            }
        });















        Picasso.with(this)
                .load(movie.getPosterPath())
                .resize(getResources().getInteger(R.integer.tmdb_poster_w185_width),
                        getResources().getInteger(R.integer.tmdb_poster_w185_height))
                .error(R.drawable.not_found)
                .placeholder(R.drawable.searching)
                .into(ivPoster);

        String overView = movie.getOverview();
        if (overView == null) {
            tvOverView.setTypeface(null, Typeface.ITALIC);
            overView = getResources().getString(R.string.no_summary_found);
        }
        tvOverView.setText(overView);
        tvVoteAverage.setText(movie.getDetailedVoteAverage());

        // First get the release date from the object - to be used if something goes wrong with
        // getting localized release date (catch).
        String releaseDate = movie.getReleaseDate();
        if(releaseDate != null) {
            try {
                releaseDate = DateTimeShortForm.getLocalizedDate(this,
                        releaseDate, movie.getDateFormat());
            } catch (ParseException e) {
                Log.e(LOG_TAG, "Error can't parse the movie release date", e);
            }
        } else {
            tvReleaseDate.setTypeface(null, Typeface.ITALIC);
            releaseDate = getResources().getString(R.string.no_release_date_found);
        }
        tvReleaseDate.setText(releaseDate);

        //FIN FAVORIS







        //DEBUT AJOUT REVIEWS AND TRAILERS

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //TRAILERS
        final RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.trailers_recycler_view);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));






        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ReviewResponse> call = apiService.getReviews(movie.getId(),API_KEY);
        //TRAILER
        Call<TrailerResponse> call_trailer = apiService.getVideos(movie.getId(),API_KEY);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse>call, Response<ReviewResponse> response) {
                List<Review> reviews = response.body().getResults();
                recyclerView.setAdapter(new ReviewsAdapter(reviews, R.layout.list_item_review, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<ReviewResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(LOG_TAG, t.toString());
            }

        });

        //TRAILER
        call_trailer.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse>call, Response<TrailerResponse> response) {
                List<Trailer> trailers = response.body().getResults();
                recyclerView1.setAdapter(new TrailersAdapter(trailers, R.layout.list_item_trailer, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<TrailerResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(LOG_TAG, t.toString());
            }

        });















    }







}


