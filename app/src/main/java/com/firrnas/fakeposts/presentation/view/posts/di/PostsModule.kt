package com.firrnas.fakeposts.presentation.view.posts.di

import com.firrnas.fakeposts.data.repository.Repository
import com.firrnas.fakeposts.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PostsModule {
    @Provides
    @ViewModelScoped
    fun providePostsUseCase(repository: Repository): GetPostsUseCase {
        return GetPostsUseCase(repository)
    }
}