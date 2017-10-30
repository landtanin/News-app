package com.landtanin.news;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.landtanin.news.model.Article;
import com.landtanin.news.utils.DateUtils;

import java.util.List;

/**
 * Created by landtanin on 29/09/2017.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder> {

    private List<Article> newsArticles;
    private FirebaseAnalytics mFirebaseAnalytics;

    public HomeNewsAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @Override
    public HomeNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        return new HomeNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeNewsViewHolder holder, final int position) {

        Article newsArticle = newsArticles.get(position);
        Glide.with(holder.cardImageView.getContext()).load(newsArticle.getUrlToImage())
                .centerCrop()
                .into(holder.cardImageView);
        holder.cardTitleTextView.setText(newsArticle.getTitle());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle.getPublishedAt()));
        holder.cardContentTextView.setText(newsArticle.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseAnalytics = FirebaseAnalytics.getInstance(view.getContext());
                Bundle bundle = new Bundle();
                bundle.putString("index", String.valueOf(position));
                mFirebaseAnalytics.logEvent("cardClicked", bundle);
                NewsDetailsActivity.launch(view.getContext(), position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public static class HomeNewsViewHolder extends RecyclerView.ViewHolder{

        ImageView cardImageView;
        TextView cardTitleTextView, cardTimeTextView, cardContentTextView;

        public HomeNewsViewHolder(View itemView) {
            super(itemView);

            cardImageView = itemView.findViewById(R.id.card_news_image);
            cardTitleTextView = itemView.findViewById(R.id.card_news_title);
            cardTimeTextView = itemView.findViewById(R.id.card_news_time);
            cardContentTextView = itemView.findViewById(R.id.card_news_content);
        }
    }

}
