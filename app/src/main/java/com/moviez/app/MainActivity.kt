package com.moviez.app

import android.os.Bundle
import dagger.android.DaggerActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : DaggerActivity(), HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}