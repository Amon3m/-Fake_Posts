package com.firrnas.fakeposts.domain.repository

import com.firrnas.fakeposts.domain.model.PostsModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepositoryInterface {

    suspend fun getPosts(): Flow<Response<List<PostsModel>>>
    suspend fun getPostByUserId(id:String): Flow<Response<PostsModel>>
}