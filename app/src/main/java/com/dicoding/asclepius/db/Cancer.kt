package com.dicoding.asclepius.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cancer")
data class Cancer (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "imagePath")
    val imagePath: String,
    @ColumnInfo(name = "prediction")
    val prediction: String,
    @ColumnInfo(name = "confidence")
    val confidence: Float,
)