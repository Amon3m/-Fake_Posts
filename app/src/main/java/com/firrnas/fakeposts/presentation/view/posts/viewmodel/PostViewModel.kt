package com.firrnas.fakeposts.presentation.view.posts.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firrnas.fakeposts.domain.usecase.GetPostsUseCase
import com.firrnas.fakeposts.presentation.mapper.toPostUiModel
import com.firrnas.fakeposts.presentation.view.posts.view.PostState
import com.firrnas.fakeposts.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class PostViewModel @Inject constructor(private val useCase: GetPostsUseCase) : ViewModel() {
    private val _postState: MutableStateFlow<PostState.Display> =
        MutableStateFlow(PostState.Display())
    val userState = _postState.asStateFlow()
    private val _errorState: MutableSharedFlow<PostState.Failure> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getPost() {
        _postState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {

            useCase.getPost().collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            Log.d("PostViewModel", "Data: $it")
                            _postState.update { state ->
                                state.copy(
                                    postUiModel = response.data.map {
                                        it.toPostUiModel()
                                    }, loading = false
                                )
                            }
                        }
                    }

                    is Response.Failure -> {

                        response.error?.let { errorMessage ->
                            Log.e("PostViewModel", "Data: $errorMessage")

                            _errorState.emit(PostState.Failure(errorMessage))
                        }
                        _postState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}