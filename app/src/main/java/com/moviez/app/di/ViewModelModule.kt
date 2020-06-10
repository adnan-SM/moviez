package com.moviez.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviez.app.MovieListViewModel
import com.moviez.app.ViewModelFactory
import com.moviez.app.di.scopes.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author adnan
 * @since  10/06/20
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindThemeViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}