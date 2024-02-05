package com.jesse.securedatabaseapp.data.repository

import com.jesse.securedatabaseapp.data.model.User

interface UserRepository {

    suspend fun getUser(userId: Int): User
}