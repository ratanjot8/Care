package com.example.care.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerData(
    @PrimaryKey val cid: Int,
    val name: String,
    val age: String,
    val dob: String,
    val address: String
)
