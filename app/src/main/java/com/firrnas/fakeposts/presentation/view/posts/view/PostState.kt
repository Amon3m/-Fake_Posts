package com.firrnas.fakeposts.presentation.view.posts.view

import com.firrnas.fakeposts.presentation.model.PostUIModel

sealed class PostState {

    data class Display(
        var postUiModel : List<PostUIModel> = listOf( PostUIModel(
            body = "", id = 1, title = "", userId = 1)
        ),
        val loading: Boolean = false
    ) : PostState()

    data class Failure(val errorMsg: String = "") : PostState()
}