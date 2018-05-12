package com.firstpart.movie.popmovie1_yohan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Yohan on 03/05/2018.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {

    private List<Trailer> trailers;
    private int rowLayout;
    private Context context;


    public static class TrailersViewHolder extends RecyclerView.ViewHolder {
        LinearLayout trailers_layout;
        TextView TrailerName;
        TextView TrailerKey;


        public TrailersViewHolder(View v) {
            super(v);
            trailers_layout = (LinearLayout) v.findViewById(R.id.trailers_layout);
            TrailerName = (TextView) v.findViewById(R.id.tv_trailer_name);
            TrailerKey = (TextView) v.findViewById(R.id.tv_key);




        }


    }

    public TrailersAdapter(List<Trailer> trailers, int rowLayout, Context context) {
        this.trailers = trailers;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public TrailersAdapter.TrailersViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TrailersViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TrailersViewHolder holder, final int position) {
        holder.TrailerName.setText(trailers.get(position).getName());
        holder.TrailerKey.setText(trailers.get(position).getKey());
        //ACTION A CHAQUE CLIC SUR ITEM DE LA RECYCLERVIEW
        holder.trailers_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CONSTRUIT L'URL A OUVRIR DANS UN BROWSER
                Uri builtUri = Uri.parse("https://www.youtube.com/watch").buildUpon()
                        .appendQueryParameter("v", trailers.get(position).getKey()) //add the api key
                        .build();
                Toast.makeText(context, "YOUTUBE TRAILER LINK : "+ builtUri, Toast.LENGTH_LONG).show();
                //INTENT TO OPEN THE BROWSER TRAILER
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(builtUri);
                context.startActivity(i);



            }
        });//FIN ACTION SUR CLICK ITEM TRAILER


    }



    @Override
    public int getItemCount() {
        return trailers.size();
    }


}

