package com.moviez.app

import androidx.lifecycle.*
import com.moviez.app.model.MovieSearchResponse.Movie
import com.moviez.app.model.Page
import com.moviez.app.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.max

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