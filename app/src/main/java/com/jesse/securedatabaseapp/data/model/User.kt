package com.jesse.securedatabaseapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "user_name")
    val userName: String = "",
    @ColumnInfo(name = "user_email")
    val userEmail: String = "",
    @ColumnInfo(name = "user_token")
    val userToken: String = ""
)
