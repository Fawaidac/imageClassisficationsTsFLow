package com.dicoding.asclepius.repository

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.db.Cancer
import com.dicoding.asclepius.db.CancerDao

class CancerRepository(private val cancerDao: CancerDao) {

    fun getAll(): LiveData<List<Cancer>> = cancerDao.getAllResults()

    fun insert(cancer: Cancer){
        cancerDao.insertResult(cancer)
    }

}