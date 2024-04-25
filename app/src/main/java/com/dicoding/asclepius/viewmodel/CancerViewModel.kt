package com.dicoding.asclepius.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.asclepius.db.Cancer
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.db.CancerDatabase
import com.dicoding.asclepius.repository.CancerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CancerViewModel(application: Application) : AndroidViewModel(application) {
    private val _cancer = MutableLiveData<List<Cancer>>()
    var cancer: LiveData<List<Cancer>> = _cancer

    private val cancerRepository: CancerRepository

    init {
        val cancerDao = CancerDatabase.getDatabase(application).cancerDao()
        cancerRepository = CancerRepository(cancerDao)
        cancer = cancerRepository.getAll()
    }

    fun insert(cancer: Cancer){
        viewModelScope.launch(Dispatchers.IO) {
            cancerRepository.insert(cancer)
        }
    }

}