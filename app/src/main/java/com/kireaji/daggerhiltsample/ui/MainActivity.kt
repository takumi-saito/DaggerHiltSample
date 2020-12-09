package com.kireaji.daggerhiltsample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kireaji.daggerhiltsample.R
import com.kireaji.daggerhiltsample.data.repository.GithubRepository
import com.kireaji.daggerhiltsample.di.ActivityObject
import com.kireaji.daggerhiltsample.di.AppObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appObject: AppObject
    @Inject
    lateinit var activityObject: ActivityObject
    @Inject
    lateinit var repository: GithubRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        findViewById<Button>(R.id.button_1).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    repository.searchRepos("takumi-saito")
                }
                result.forEach {
                    Timber.d("$it")
                }
            }
        }

        findViewById<Button>(R.id.button_2).setOnClickListener {
            startActivity(
                Intent(this, SecondActivity::class.java)
            )
        }

        Timber.d("appObject hash:${appObject.hash()}")
        Timber.d("activityObject hash:${activityObject.hash()}")
    }
}