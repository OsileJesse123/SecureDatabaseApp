package com.jesse.securedatabaseapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesse.securedatabaseapp.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
}