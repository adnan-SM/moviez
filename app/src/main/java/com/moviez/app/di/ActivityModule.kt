package com.moviez.app.di

import com.moviez.app.ui.MainActivity
import com.moviez.app.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author adnan
 * @since  09/06/20
 */
@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailActivity(): MainActivity
}