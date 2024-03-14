package com.firrnas.fakeposts.presentation.view.postdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firrnas.fakeposts.domain.usecase.GetPostsByIDUseCase
import com.firrnas.fakeposts.presentation.mapper.toPostUiModel
import com.firrnas.fakeposts.presentation.view.postdetails.view.PostDetailsState
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

class PostDetailsViewModel  @Inject constructor(val useCase: GetPostsByIDUseCase) : ViewModel() {
    private val _postDetailsState: MutableStateFlow<PostDetailsState.Display> =
        MutableStateFlow(PostDetailsState.Display())
    val postDetailsState = _postDetailsState.asStateFlow()
    private val _errorState: MutableSharedFlow<PostDetailsState.Failure> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getDataByUserId(id: String) {
        _postDetailsState.update {
            it.copy(loading = true)
        }

        viewModelScope.launch {
            useCase.getDataByUserId(id).collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _postDetailsState.update { state ->
                                state.copy(
                                    postUIModel = response.data.toPostUiModel(),loading = false
                                )
                            }
                        }

                    }

                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(PostDetailsState.Failure(errorMessage))
                        }
                        _postDetailsState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}