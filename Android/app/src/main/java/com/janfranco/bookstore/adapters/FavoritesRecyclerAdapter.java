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
import com.janfranco.bookstore.entities.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerAdapter.BookHolder> {

    private final ArrayList<Book> mFavoriteBooks;
    private final Context mContext;

    public FavoritesRecyclerAdapter(List<Book> favoriteBooks, Context context) {
        mFavoriteBooks = (ArrayList<Book>) favoriteBooks;
        mContext = context;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row_favorite_book, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book book = mFavoriteBooks.get(position);

        Resources res = mContext.getResources();
        String baseAvatarUrl = res.getString(res.getIdentifier("R.string.api_service_avatar_url",
                "string", mContext.getPackageName()));

        Picasso.get().load(baseAvatarUrl + book.getCover()).into(holder.bookCover);
        holder.titleText.setText(book.getTitle());
        holder.authorText.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return mFavoriteBooks.size();
    }

    static class BookHolder extends RecyclerView.ViewHolder {

        ImageView bookCover;
        TextView titleText;
        TextView authorText;

        public BookHolder(@NonNull View itemView) {
            super(itemView);

            bookCover = itemView.findViewById(R.id.recycler_row_favorite_book_cover);
            titleText = itemView.findViewById(R.id.recycler_row_favorite_book_title);
            authorText = itemView.findViewById(R.id.recycler_row_favorite_book_author);
        }

    }

}
