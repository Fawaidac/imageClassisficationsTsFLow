package com.dicoding.asclepius.services

import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.model.News
import retrofit2.http.GET


interface ApiServices {
        @GET("/v2/top-headlines?q=cancer&category=health&language=en&apiKey=${BuildConfig.API_TOKEN}")
        suspend fun getTopHeadlines(): News
    }
