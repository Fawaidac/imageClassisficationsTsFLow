package com.dicoding.asclepius.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CancerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(cancer: Cancer)

    @Query("SELECT * FROM cancer")
    fun getAllResults(): LiveData<List<Cancer>>
}