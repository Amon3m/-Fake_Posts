package com.firrnas.fakeposts.presentation.model

import java.io.Serializable

data class PostUIModel( val body: String?,
                        val id: Int?,
                        val title: String?,
                        val userId: Int?
):Serializable
