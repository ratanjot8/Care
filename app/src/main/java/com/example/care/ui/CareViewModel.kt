package com.example.care.ui

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.care.data.CareRepository
import com.example.care.data.database.entity.CustomerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CareViewModel: ViewModel() {
    private var careRepository= CareRepository()

    fun insertDataToDb(context: Context, customerData: CustomerData) {
        viewModelScope.launch(Dispatchers.IO) {
            careRepository.insert(context, customerData)
        }
    }
}