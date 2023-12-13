package com.example.randomusertest.di

import com.example.randomusertest.data.RandomUserService
import com.example.randomusertest.data.UserRepositoryImpl
import com.example.randomusertest.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideRandomUserService(retrofit: Retrofit): RandomUserService =
        retrofit.create(RandomUserService::class.java)

    @Provides
    fun provideUserRepository(randomUserService: RandomUserService): UserRepository {
        return UserRepositoryImpl(randomUserService)
    }

}