package com.example.care.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.care.data.database.entity.CustomerData

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertAll(customerData: CustomerData)
}