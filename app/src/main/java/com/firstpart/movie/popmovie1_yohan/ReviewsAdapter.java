package com.firstpart.movie.popmovie1_yohan;

/**
 * Created by Yohan on 02/05/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import com.firstpart.movie.popmovie1_yohan.Review;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private List<Review> reviews;
    private int rowLayout;
    private Context context;


    public static class ReviewsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout reviewsLayout;
        TextView reviewAuthor;
        TextView reviewContent;

        public ReviewsViewHolder(View v) {
            super(v);
            reviewsLayout = (LinearLayout) v.findViewById(R.id.reviews_layout);
            reviewAuthor = (TextView) v.findViewById(R.id.tv_author);
            reviewContent = (TextView) v.findViewById(R.id.tv_content);

        }
    }

    public ReviewsAdapter(List<Review> reviews, int rowLayout, Context context) {
        this.reviews = reviews;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ReviewsAdapter.ReviewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ReviewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, final int position) {
        holder.reviewAuthor.setText(reviews.get(position).getAuthor());
        holder.reviewContent.setText(reviews.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}