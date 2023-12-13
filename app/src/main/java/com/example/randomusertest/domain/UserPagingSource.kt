package com.example.randomusertest.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomusertest.data.RandomUserService

class UserPagingSource(private val userService: RandomUserService) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 1
            val response = userService.getUsers(page = currentPage, results = params.loadSize)
            val data = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}