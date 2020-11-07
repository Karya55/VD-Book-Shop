package com.janfranco.bookstore.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.janfranco.bookstore.R;
import com.janfranco.bookstore.entities.Review;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserReviewsRecyclerAdapter extends RecyclerView.Adapter<UserReviewsRecyclerAdapter.ReviewHolder> {

    private final ArrayList<Review> mReviews;
    private final Context mContext;

    public UserReviewsRecyclerAdapter(List<Review> userReviews, Context context) {
        mReviews = (ArrayList<Review>) userReviews;
        mContext = context;
    }

    @NonNull
    @Override
    public UserReviewsRecyclerAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row_user_review, parent, false);
        return new UserReviewsRecyclerAdapter.ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserReviewsRecyclerAdapter.ReviewHolder holder, int position) {
        Review review = mReviews.get(position);

        Resources res = mContext.getResources();
        String baseAvatarUrl = res.getString(res.getIdentifier("R.string.api_service_avatar_url",
                "string", mContext.getPackageName()));

        Picasso.get().load(baseAvatarUrl + review.getBook().getCover()).into(holder.bookCover);
        holder.titleText.setText(review.getBook().getTitle());
        holder.authorText.setText(review.getBook().getAuthor());

        if (review.getReview() != null)
            holder.reviewText.setText(review.getReview());

        holder.starText.setText(String.valueOf(review.getStar()));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    static class ReviewHolder extends RecyclerView.ViewHolder {

        ImageView bookCover;
        TextView titleText;
        TextView authorText;
        TextView reviewText;
        TextView starText;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);

            bookCover = itemView.findViewById(R.id.recycler_row_user_review_book_cover);
            titleText = itemView.findViewById(R.id.recycler_row_user_review_book_title);
            authorText = itemView.findViewById(R.id.recycler_row_user_review_book_author);
            reviewText = itemView.findViewById(R.id.recycler_row_user_review);
            starText = itemView.findViewById(R.id.recycler_row_user_review_star);
        }

    }

}
