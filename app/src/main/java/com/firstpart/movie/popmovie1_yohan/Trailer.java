package com.firstpart.movie.popmovie1_yohan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yohan on 03/05/2018.
 */

public class Trailer {
    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;


    public Trailer(String name, String key) {

        this.name = name;
        this.key = key;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
