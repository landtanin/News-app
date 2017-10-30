package com.landtanin.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.landtanin.news.model.GetArticleResponse;
import com.landtanin.news.networking.NewsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);

        // for recyclerview
        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(NewsStore.getNewsArticles());
//        newsRecyclerView.setAdapter(homeNewsAdapter);

        progressBar = (ProgressBar) findViewById(R.id.activity_main_progressbar);

        Call<GetArticleResponse> call = NewsAPI.getApi().getArticles("the-verge", "top");
        call.enqueue(new Callback<GetArticleResponse>() {
            @Override
            public void onResponse(Call<GetArticleResponse> call, Response<GetArticleResponse> response) {

                progressBar.setVisibility(View.GONE);

                showNewsApiSnack();

                GetArticleResponse getArticleResponse = response.body();

                NewsStore.setNewsArticles(getArticleResponse.getArticles());

                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticleResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticleResponse> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

            }
        });

    }

    private void showNewsApiSnack() {

        Snackbar.make(coordinatorLayout, "Powered by NewsApi.org", Snackbar.LENGTH_LONG)
            .setAction("Visit", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    loadNewsApiWebsite();

                }
            }).show();

    }

    private void loadNewsApiWebsite() {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org")));

    }

}
