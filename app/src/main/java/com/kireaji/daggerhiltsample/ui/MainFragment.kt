package com.kireaji.daggerhiltsample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kireaji.daggerhiltsample.R
import com.kireaji.daggerhiltsample.di.ActivityObject
import com.kireaji.daggerhiltsample.di.AppObject
import com.kireaji.daggerhiltsample.di.FragmentObject
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var appObject: AppObject
    @Inject
    lateinit var activityObject: ActivityObject
    @Inject
    lateinit var fragmentObject: FragmentObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("appObject hash:${appObject.hash()}")
        Timber.d("activityObject hash:${activityObject.hash()}")
        Timber.d("fragmentObject hash:${fragmentObject.hash()}")
    }
}