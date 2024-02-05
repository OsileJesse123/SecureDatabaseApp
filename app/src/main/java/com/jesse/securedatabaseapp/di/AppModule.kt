package com.jesse.securedatabaseapp.di

import android.content.Context
import androidx.room.Room
import com.jesse.securedatabaseapp.data.database.UserDatabase
import com.jesse.securedatabaseapp.data.database.UserDatabasePassphrase
import com.jesse.securedatabaseapp.data.repository.UserRepository
import com.jesse.securedatabaseapp.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideUserDatabasePassphrase(@ApplicationContext context: Context) = UserDatabasePassphrase(context)

    @Provides
    @Singleton
    fun provideSupportFactory(userDatabasePassphrase: UserDatabasePassphrase) = SupportFactory(userDatabasePassphrase.getPassphrase())

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, supportFactory: SupportFactory): UserDatabase{
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
            .openHelperFactory(supportFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(userDatabase: UserDatabase) = userDatabase.getUserDao()

    @Provides
    fun provideUserRepository(userRepoImpl: UserRepositoryImpl): UserRepository = userRepoImpl

}