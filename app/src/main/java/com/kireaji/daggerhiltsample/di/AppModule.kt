package com.kireaji.daggerhiltsample.di

import com.kireaji.daggerhiltsample.data.api.GithubClient
import com.kireaji.daggerhiltsample.data.api.GithubService
import com.kireaji.daggerhiltsample.data.repository.GithubRepository
import com.kireaji.daggerhiltsample.data.repository.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppObject(): AppObject {
        return AppObject()
    }

    @Singleton
    @Provides
    fun provideRepository(client: GithubClient): GithubRepository {
        return GithubRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideClient(service: GithubService): GithubClient {
        return GithubClient(service)
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}