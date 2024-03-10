package com.firrnas.fakeposts.data.remote

import com.firrnas.fakeposts.data.model.PostsEntity
import com.firrnas.fakeposts.data.model.PostsEntityItem
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    suspend fun getUsers(): Flow<PostsEntity>
    suspend fun getDataByUserId(id:String): Flow<PostsEntityItem>
}