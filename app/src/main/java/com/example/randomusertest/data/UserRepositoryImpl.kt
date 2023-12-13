package com.example.randomusertest.data

import com.example.randomusertest.domain.User
import com.example.randomusertest.domain.UserRepository
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val randomUserService: RandomUserService
): UserRepository {

    override suspend fun getUsers(page: Int): List<User> {
        val resultsPerPage = 20
        try {
            val response = randomUserService.getUsers(resultsPerPage, page)
            if (response.isSuccessful) {
                return response.body()?.results ?: emptyList()
            } else {
                throw Exception("Failed to fetch users")
            }
        } catch (e: Exception) {
            throw Exception("Something went wrong")
        }
    }
}