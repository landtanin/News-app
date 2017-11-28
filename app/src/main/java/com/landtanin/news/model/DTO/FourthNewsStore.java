package com.landtanin.news.model.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landtanin on 29/09/2017.
 */

public class FourthNewsStore {

    // make it static to be able to access from anywhere
    private static List<Article> newsArticles = new ArrayList<>();

    public static List<Article> getNewsArticles() {
        return newsArticles;
    }

    public static void  setNewsArticles(List<Article> newsArticles) {
        FourthNewsStore.newsArticles = newsArticles;
    }
}
