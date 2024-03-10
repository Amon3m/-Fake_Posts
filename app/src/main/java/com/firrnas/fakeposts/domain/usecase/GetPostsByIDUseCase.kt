package com.firrnas.fakeposts.domain.usecase

import com.firrnas.fakeposts.domain.model.PostsModel
import com.firrnas.fakeposts.domain.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetPostsByIDUseCase@Inject constructor(private val repository: RepositoryInterface) {
    suspend fun getDataByUserId(id: String): Flow<Response<PostsModel>> {
        return repository.getPostByUserId(id)
    }
}