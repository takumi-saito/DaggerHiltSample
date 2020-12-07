package com.kireaji.daggerhiltsample.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kireaji.daggerhiltsample.R
import com.kireaji.daggerhiltsample.data.api.GithubClient
import com.kireaji.daggerhiltsample.data.api.GithubService
import com.kireaji.daggerhiltsample.data.repository.GithubRepository
import com.kireaji.daggerhiltsample.data.repository.GithubRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        findViewById<Button>(R.id.button_1).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    provideRepository().searchRepos("takumi-saito")
                }
                result.forEach {
                    Timber.d("$it")
                }
            }
        }
    }

    fun provideRepository(): GithubRepository {
        return GithubRepositoryImpl.getInstance(
            provideClient()
        )
    }

    fun provideClient(): GithubClient {
        return GithubClient.getInstance(
            provideService()
        )
    }

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