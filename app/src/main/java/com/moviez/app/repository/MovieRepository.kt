package com.moviez.app.repository

import androidx.lifecycle.LiveData
import com.moviez.app.Result
import com.moviez.app.model.MovieSearchResponse.Movie

/**
 * @author adnan
 * @since  09/06/20
 */

interface MovieRepository {

      fun getMovieList(searchTerm: String, pageNumber: Int): LiveData<Result<List<Movie>>>
}