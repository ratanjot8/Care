package com.example.care.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.care.data.database.dao.CustomerDao
import com.example.care.data.database.entity.CustomerData

@Database(entities = [CustomerData::class], version = 1)
abstract class CareDatabase: RoomDatabase() {
    abstract fun getCustomerDao(): CustomerDao

    companion object {
        @Volatile
        private var INSTANCE: CareDatabase?=null

        fun getDatabase(context: Context): CareDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CareDatabase::class.java,
                    "care_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}