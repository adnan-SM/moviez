package com.moviez.app.api

import com.moviez.app.api.DataSource
import com.moviez.app.api.retrofit.MoviezApiService
import javax.inject.Inject

/**
 * @author adnan
 * @since  09/06/20
 */
class RemoteDataSource @Inject constructor(private val moviezApiService: MoviezApiService): DataSource() {

    suspend fun getMovies(searchTerm: String, pageNum: Int) = mapResultObject {
        moviezApiService.getMovieList(searchTerm, pageNum)
    }
}