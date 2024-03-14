package com.firrnas.fakeposts.domain.usecase

import com.firrnas.fakeposts.domain.model.PostsModel
import com.firrnas.fakeposts.domain.repository.RepositoryInterface
import com.firrnas.fakeposts.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase@Inject constructor(private val repository: RepositoryInterface) {
    suspend fun getPost(): Flow<Response<List<PostsModel>>> {
        return repository.getPosts()
    }
}