package com.moviez.app

import com.moviez.app.di.DaggerAppComponent
import dagger.android.DaggerApplication

/**
 * @author adnan
 * @since  09/06/20
 */
class MoviezApplication: DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)

    override fun applicationInjector() = appComponent
}