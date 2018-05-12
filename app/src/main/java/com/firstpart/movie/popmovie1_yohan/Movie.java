package com.firstpart.movie.popmovie1_yohan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yohan on 28/03/2018.
 */

public class Movie implements Parcelable {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private String mOriginalTitle;
    private String mPosterPath;
    private String mOverview;
    private Double mVoteAverage;
    private String mReleaseDate;
    private String mid;

    //For the Movie Object
    public Movie() {
    }

    //Sets the movie's title
    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public void setId(String id) {
        mid = id;
    }

    //Sets path to movie's poster
    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    //Sets the overview
    public void setOverview(String overview) {
        if(!overview.equals("null")) {
            mOverview = overview;
        }
    }

    //Sets the vote average
    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    //Sets the release date
    public void setReleaseDate(String releaseDate) {
        if(!releaseDate.equals("null")) {
            mReleaseDate = releaseDate;
        }
    }

    //Get the movie's title
    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getId() {
        return mid;
    }

    //Return poster's url string
    public String getPosterPath() {
        final String TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

        return TMDB_POSTER_BASE_URL + mPosterPath;
    }

    //Get the movie description
    public String getOverview() {
        return mOverview;
    }

    //Get the average score
    private Double getVoteAverage() {
        return mVoteAverage;
    }

    //Get the release date
    public String getReleaseDate() {
        return mReleaseDate;
    }

    //Get the score
    public String getDetailedVoteAverage() {
        return String.valueOf(getVoteAverage()) + "/10";
    }

    //Return date's format
    public String getDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOriginalTitle);
        dest.writeString(mid);
        dest.writeString(mPosterPath);
        dest.writeString(mOverview);
        dest.writeValue(mVoteAverage);
        dest.writeString(mReleaseDate);
    }

    private Movie(Parcel in) {
        mOriginalTitle = in.readString();
        mid = in.readString();
        mPosterPath = in.readString();
        mOverview = in.readString();
        mVoteAverage = (Double) in.readValue(Double.class.getClassLoader());
        mReleaseDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
