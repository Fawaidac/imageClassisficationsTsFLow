package com.dicoding.asclepius.viewmodel

import androidx.lifecycle.*
import com.dicoding.asclepius.model.News
import com.dicoding.asclepius.services.ApiServices
import kotlinx.coroutines.launch

class NewsViewModel(private val apiServices: ApiServices) : ViewModel() {

    private val _news = MutableLiveData<News>()
    val news: LiveData<News>
        get() = _news

    fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = apiServices.getTopHeadlines()
                _news.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class NewsViewModelFactory(private val apiServices: ApiServices) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(apiServices) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}