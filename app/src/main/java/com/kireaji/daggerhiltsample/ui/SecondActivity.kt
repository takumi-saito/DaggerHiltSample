package com.kireaji.daggerhiltsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kireaji.daggerhiltsample.R
import com.kireaji.daggerhiltsample.di.ActivityObject
import com.kireaji.daggerhiltsample.di.AppObject
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var appObject: AppObject
    @Inject
    lateinit var activityObject: ActivityObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Timber.d("appObject hash:${appObject.hash()}")
        Timber.d("activityObject hash:${activityObject.hash()}")
    }
}