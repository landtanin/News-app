package com.landtanin.news.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.landtanin.news.R;
import com.landtanin.news.databinding.FragmentNewsBinding;
import com.landtanin.news.model.DTO.GetArticleResponse;
import com.landtanin.news.model.DTO.NewsStore;
import com.landtanin.news.networking.NewsAPI;
import com.landtanin.news.ui.HomeNewsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class NewsFragment extends Fragment {

    FragmentNewsBinding b;
    private String newsSource;
    private static final String NEWS_RESOURCE_KEY = "NEWS_RESOURCE_KEY";

    public NewsFragment() {
        super();
    }

    public static NewsFragment newInstance(String newsSource) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(NEWS_RESOURCE_KEY, newsSource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
        else restoreArguments(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        View rootView = b.getRoot();
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        Log.d("NewsFragmeng", "initInstances: newsSource = " + newsSource);

        b.newsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<GetArticleResponse> call = NewsAPI.getApi().getArticles(newsSource);
        call.enqueue(new Callback<GetArticleResponse>() {
            @Override
            public void onResponse(Call<GetArticleResponse> call, Response<GetArticleResponse> response) {

                b.progressbar.setVisibility(View.GONE);

//                showNewsApiSnack();

                GetArticleResponse getArticleResponse = response.body();

                NewsStore.setNewsArticles(getArticleResponse.getArticles());

                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticleResponse.getArticles());
                b.newsRecyclerview.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticleResponse> call, Throwable t) {

                b.progressbar.setVisibility(View.GONE);

            }
        });

    }

    private void showNewsApiSnack() {

        Snackbar.make(b.newsFragmentLayout, "Powered by NewsApi.org", Snackbar.LENGTH_LONG)
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
        outState.putString(NEWS_RESOURCE_KEY, newsSource);

    }

    private void restoreArguments(Bundle arguments) {
        // initialise from arguments when no states saved
        newsSource = arguments.getString(NEWS_RESOURCE_KEY);

    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
        newsSource = savedInstanceState.getString(NEWS_RESOURCE_KEY);
    }



}
