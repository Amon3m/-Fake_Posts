package com.firrnas.fakeposts.domain.usecase

import com.firrnas.fakeposts.domain.model.PostsModel
import com.firrnas.fakeposts.domain.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetPostsUseCase@Inject constructor(private val repository: RepositoryInterface) {
    suspend fun getUser(): Flow<Response<List<PostsModel>>> {
        return repository.getPosts()
    }
}