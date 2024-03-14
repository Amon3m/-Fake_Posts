package com.firrnas.fakeposts.di

import com.firrnas.fakeposts.data.remote.ApiClient
import com.firrnas.fakeposts.data.remote.RemoteSource
import com.firrnas.fakeposts.data.repository.Repository
import com.firrnas.fakeposts.domain.repository.RepositoryInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton
//
//@Module
//abstract class RepoModule {
//    @Binds
//    @Singleton
//    abstract fun provideRemoteSource(apiClient: ApiClient ): RemoteSource
//
//    @Binds
//    @Singleton
//    abstract fun provideRepository(repository: Repository): RepositoryInterface
//
//}