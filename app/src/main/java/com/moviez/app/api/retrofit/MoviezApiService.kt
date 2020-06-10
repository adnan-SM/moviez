package com.moviez.app.api.retrofit

import com.moviez.app.model.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Adnan
 * @since  09/06/20
 */
interface MoviezApiService {

    @GET("/")
    suspend fun getMovieList(
        @Query("s") searchTerm: String,
        @Query("page") pageNum: Int
    ) : Response<MovieSearchResponse>
}