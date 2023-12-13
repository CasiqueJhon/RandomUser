package com.example.randomusertest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusertest.domain.User
import com.example.randomusertest.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor (
    private val userRepository: UserRepository
): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users : LiveData<List<User>> = _users

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    init {
        _currentPage.value = 1
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val page = _currentPage.value ?: 1
            try {
                val newUsers = userRepository.getUsers(page)
                _users.value = newUsers
            } catch (e: Exception) {
                throw Exception("Something went wrong")
            }
        }
    }

    fun nextPage() {
        val next = (_currentPage.value ?: 1) + 1
        _currentPage.value = next
        loadUsers()
    }
}