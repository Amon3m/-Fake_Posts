package com.firrnas.fakeposts.data.remote

import com.firrnas.fakeposts.data.model.PostsEntity
import com.firrnas.fakeposts.data.model.PostsEntityItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService) :
    RemoteSource {
    override suspend fun getUsers(): Flow<PostsEntity> {
        return flowOf( apiService.getPosts())

    }

    override suspend fun getDataByUserId(id: String): Flow<PostsEntityItem> {
        return flowOf( apiService.getPostsByUserId(id))
    }
}