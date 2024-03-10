package com.firrnas.fakeposts.data.mapper

import com.firrnas.fakeposts.data.model.PostsEntity
import com.firrnas.fakeposts.data.model.PostsEntityItem
import com.firrnas.fakeposts.domain.model.PostsModel

fun PostsEntityItem.toPostModel(): PostsModel =
    PostsModel(
        body = body, id = id, title = title, userId = userId
    )

fun PostsEntity.toPostModel(): List<PostsModel> = this.postsEntity.map{ it.toPostModel()  }
