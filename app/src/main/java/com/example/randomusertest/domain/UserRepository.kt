package com.example.randomusertest.domain

interface UserRepository {
    suspend fun getUsers(page: Int): List<User>
}