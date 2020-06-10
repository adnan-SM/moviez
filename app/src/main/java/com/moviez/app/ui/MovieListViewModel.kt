package com.moviez.app.ui

import androidx.lifecycle.*
import com.moviez.app.model.MovieSearchResponse.Movie
import com.moviez.app.model.Page
import com.moviez.app.repository.MovieRepository
import com.moviez.app.utils.Result
import javax.inject.Inject

/**
 * @author adnan
 * @since  09/06/20
 */
class MovieListViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    internal val inputSearchData by lazy { MutableLiveData<Page>() }

    internal val movieLiveData: LiveData<Result<List<Movie>>> by lazy {
        Transformations.switchMap(inputSearchData) {
            inputSearchData.value?.let {
                repository.getMovieList(it.searchTerm, it.latestPage)
            }
        }
    }

    init {
        // Initial Search Term - FRIENDS
        inputSearchData.postValue(Page("FRIENDS", 1))
    }
}