package com.dicoding.asclepius.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cancer::class], version = 1)
abstract class CancerDatabase : RoomDatabase() {
    abstract fun cancerDao(): CancerDao

    companion object {
        @Volatile
        private var INSTANCE: CancerDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CancerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CancerDatabase::class.java,
                    "cancer_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}


