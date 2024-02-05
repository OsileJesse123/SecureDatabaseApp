package com.jesse.securedatabaseapp.data.repository

import com.jesse.securedatabaseapp.data.database.UserDao
import com.jesse.securedatabaseapp.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher
): UserRepository {
    override suspend fun getUser(userId: Int): User {
        return withContext(ioDispatcher){
            val user = User(id = 1, userName = "New User", userEmail = "newuser@gmail.com", userToken = "kafjae9304efnaelfjer!338r")
            userDao.insertUser(user)
            userDao.getUser(user.id)
        }
    }
}