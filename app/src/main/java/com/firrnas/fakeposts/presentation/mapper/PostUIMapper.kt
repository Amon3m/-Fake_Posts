package com.firrnas.fakeposts.presentation.mapper

import com.firrnas.fakeposts.domain.model.PostsModel
import com.firrnas.fakeposts.presentation.model.PostUIModel

fun PostsModel.toPostUiModel(): PostUIModel =
    PostUIModel(
        body = body, id = id, title = title, userId = userId
    )