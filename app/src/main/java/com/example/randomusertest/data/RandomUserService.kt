package com.example.randomusertest.data

import com.example.randomusertest.domain.RandomUserResponse
import com.example.randomusertest.domain.User
import dagger.Provides
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int,
        @Query("page") page: Int
    ): Response<RandomUserResponse>

}