package com.example.care.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerData(
    val name: String,
    val age: Int,
    val dob: String,
    val address: String
)
