package com.firrnas.fakeposts.domain.repository

import com.firrnas.fakeposts.domain.model.PostsModel
import com.firrnas.fakeposts.util.Response
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {

    suspend fun getPosts(): Flow<Response<List<PostsModel>>>
    suspend fun getPostByUserId(id:String): Flow<Response<PostsModel>>
}