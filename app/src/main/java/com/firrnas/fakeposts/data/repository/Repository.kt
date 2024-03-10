package com.firrnas.fakeposts.data.repository

import com.firrnas.fakeposts.data.mapper.toPostModel
import com.firrnas.fakeposts.data.remote.RemoteSource
import com.firrnas.fakeposts.domain.model.PostsModel
import com.firrnas.fakeposts.domain.repository.RepositoryInterface
import com.firrnas.fakeposts.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository@Inject constructor(private val remoteSource: RemoteSource) :
    RepositoryInterface {
    override suspend fun getPosts(): Flow<Response<List<PostsModel>>> {
        return try {
            remoteSource.getUsers().map {
                Response.Success(it.toPostModel())
            }
        } catch (e: Exception) {
            flowOf(Response.Failure(e.message ?: "Unknown Exception"))
        }
    }

    override suspend fun getPostByUserId(id: String): Flow<Response<PostsModel>> {
        return try {
            remoteSource.getDataByUserId(id).map {
                Response.Success(it.toPostModel())
            }
        }catch (e:Exception){
            flowOf(Response.Failure(e.message ?: "Unknown Exception"))
        }
    }
}