package com.firrnas.fakeposts.presentation.view.postdetails.view

import com.firrnas.fakeposts.presentation.model.PostUIModel
import com.firrnas.fakeposts.presentation.view.posts.view.PostState

sealed class PostDetailsState {
    data class Display(
        var postUIModel: PostUIModel = PostUIModel(
            body = "", id = 1, title = "", userId = 1
        ),
        val loading: Boolean = false
    ) : PostDetailsState()

    data class Failure(val errorMsg: String = "") : PostDetailsState()
}