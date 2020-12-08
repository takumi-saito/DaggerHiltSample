package com.kireaji.daggerhiltsample.dagger

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
    fun provideService(): GithubService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        // create retrofit
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .build()
        return retrofit.create(GithubService::class.java)
    }

}