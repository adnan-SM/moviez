package com.moviez.app.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Adnan
 * @since  09/06/20
 */
interface MoviezApiService {

    @GET("/")
    suspend fun getMovieList(@Query("s") searchTerm: String) : Response<String>
}