package com.firrnas.fakeposts.presentation.postdetails.di

import com.firrnas.fakeposts.data.repository.Repository
import com.firrnas.fakeposts.domain.usecase.GetPostsByIDUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PostDetails {
    @Provides
    @ViewModelScoped
    fun provideUserItemUseCase(repository: Repository): GetPostsByIDUseCase {
        return GetPostsByIDUseCase(repository)
    }
}