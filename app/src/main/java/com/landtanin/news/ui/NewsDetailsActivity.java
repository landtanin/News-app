package com.landtanin.news.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.landtanin.news.R;
import com.landtanin.news.model.DTO.Article;
import com.landtanin.news.model.DTO.FifthNewsStore;
import com.landtanin.news.model.DTO.FirstNewsStore;
import com.landtanin.news.model.DTO.FourthNewsStore;
import com.landtanin.news.model.DTO.NewsStore;
import com.landtanin.news.model.DTO.SecondNewsStore;
import com.landtanin.news.model.DTO.SeventhNewsStore;
import com.landtanin.news.model.DTO.SixthNewsStore;
import com.landtanin.news.model.DTO.ThirdNewsStore;
import com.landtanin.news.utils.Constants;

import java.util.List;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "news_index";
    private static final String KEY_ID = "key_id";
    private WebView webView;
    private ProgressBar progressBar;
    private String newsSource;
    private static final String TAG = "NewsDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        // top left corner left arrow, go back to pervious page
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.activity_news_details_webview);
        progressBar = (ProgressBar) findViewById(R.id.activity_news_details_progressbar);

        int index = getIntent().getIntExtra(KEY_INDEX, -1);
        newsSource = getIntent().getStringExtra(KEY_ID);

        if (index != -1) {
            updateNewsDetails(index);

        } else {
            Toast.makeText(this, "Sorry incorrect index passed", Toast.LENGTH_SHORT).show();

        }

    }

    public void updateNewsDetails(int index) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(NewsDetailsActivity.this, "Error in loading webpage", Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(whichNewsStore().get(index).getUrl());
        getSupportActionBar().setTitle(whichNewsStore().get(index).getTitle());
    }

    // make it static to allow it to be call from anywhere
    public static void launch(Context context, int index, String providerId) {

        Log.e(TAG, "launch: " + index);
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(KEY_INDEX, index);
        intent.putExtra(KEY_ID, providerId);
        context.startActivity(intent);

    }

    private List<Article> whichNewsStore() {
        switch (newsSource) {
            case Constants.THE_VERGE:
                return FirstNewsStore.getNewsArticles();
            case Constants.BBC:
                return SecondNewsStore.getNewsArticles();
            case Constants.ABC_NEWS:
                return ThirdNewsStore.getNewsArticles();
            case Constants.BUZZ_FEED:
                return FourthNewsStore.getNewsArticles();
            case Constants.CNN:
                return FifthNewsStore.getNewsArticles();
            case Constants.THE_NEW_YORK_TIMES:
                return SixthNewsStore.getNewsArticles();
            case Constants.REUTERS:
                return SeventhNewsStore.getNewsArticles();
            default:
                return NewsStore.getNewsArticles();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
