package com.landtanin.news.networking;

import com.landtanin.news.model.DTO.GetArticleResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by landtanin on 01/10/2017.
 */

public class NewsAPI {

//    private static final String APIKEY = "381f45477469490fa3dcf1c8fbbc958f";
    private static final String APIKEY = "6bbacaed780040d18a3417ba90a7cf54";
    private static final String APIPATH = "https://newsapi.org/v2/";


    // make it a Singleton
    private static NewsService newsService = null;

    public static NewsService getApi() {
        if (newsService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);

        }

        return newsService;
    }

    public interface NewsService {

        @GET("top-headlines?apiKey=" + APIKEY)
//        Call<GetArticleResponse> getArticles(@Query("source") String source,@Query("sortBy") String sortBy);
        Call<GetArticleResponse> getArticles(@Query("sources") String source);

    }

}

