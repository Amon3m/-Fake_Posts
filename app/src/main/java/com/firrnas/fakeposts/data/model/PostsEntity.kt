package com.firrnas.fakeposts.data.model

import com.google.gson.annotations.SerializedName

typealias PostsEntity = List<PostsEntityItem>

data class PostsEntityItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
