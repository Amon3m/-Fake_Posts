package com.firrnas.fakeposts.data.remote

import com.firrnas.fakeposts.data.model.PostsEntity
import com.firrnas.fakeposts.data.model.PostsEntityItem
import com.firrnas.fakeposts.domain.model.PostsModel
import retrofit2.http.GET
import retrofit2.http.Path



interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): PostsEntity

    @GET("/posts/{id}")
    suspend fun getPostsByUserId(@Path("id") id: String): PostsEntityItem
}