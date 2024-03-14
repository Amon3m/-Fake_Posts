package com.firrnas.fakeposts.di

import com.firrnas.fakeposts.data.remote.ApiClient
import com.firrnas.fakeposts.data.remote.ApiService
import com.firrnas.fakeposts.data.remote.RemoteSource
import com.firrnas.fakeposts.data.repository.Repository
import com.firrnas.fakeposts.domain.repository.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            return logging
        }

        @Provides
        @Singleton
        fun providesOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(okHttpLoggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideUrl(): String = "https://jsonplaceholder.typicode.com"

        @Provides
        @Singleton
        fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): ApiService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(baseUrl)
                .build().create(ApiService::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun provideRemoteSource(apiClient: ApiClient): RemoteSource

    @Binds
    @Singleton
    abstract fun provideRepository(repository: Repository): RepositoryInterface
}