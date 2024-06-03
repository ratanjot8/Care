package com.example.care.data

import android.content.Context
import com.example.care.data.database.CareDatabase
import com.example.care.data.database.entity.CustomerData

class CareRepository {

    suspend fun insert(context: Context, customerData: CustomerData) {
        CareDatabase.getDatabase(context).getCustomerDao().insertAll(customerData)
    }
}