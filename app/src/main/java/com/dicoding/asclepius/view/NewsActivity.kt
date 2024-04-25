package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.AdapterNews
import com.dicoding.asclepius.adapter.ResultAdapter
import com.dicoding.asclepius.services.RetrofitServices.apiServices
import com.dicoding.asclepius.viewmodel.CancerViewModel
import com.dicoding.asclepius.viewmodel.NewsViewModel

class NewsActivity : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var adapterNews: AdapterNews
    private lateinit var rvNews: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsViewModelFactory = NewsViewModel.NewsViewModelFactory(apiServices)
        newsViewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)


        adapterNews = AdapterNews(emptyList())
        rvNews = findViewById(R.id.newsRv)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapterNews

        newsViewModel.news.observe(this) { news ->
            adapterNews.updateData(news.articles)
        }

        newsViewModel.fetchNews()
    }
}