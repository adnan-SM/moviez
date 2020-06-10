package com.moviez.app.api

import com.moviez.app.db.MovieDao
import com.moviez.app.db.PageDao
import com.moviez.app.model.MovieSearchResponse.Movie
import com.moviez.app.model.Page
import javax.inject.Inject

/**
 * @author adnan
 * @since  09/06/20
 */
class LocalDataSource @Inject constructor(private val movieDao: MovieDao, private val pageDao: PageDao) {

    fun getMovies(searchTerm: String) =   movieDao.getMovieList(searchTerm)

    fun saveMovieList(searchTerm: String, movies: List<Movie>) = movieDao.insertMovieList(movies)

    fun updateSearchPage(searchTerm: String, latestPage: Int) = pageDao.addPage(Page(searchTerm, latestPage))

    fun getMovieList(searchTerm: String) = movieDao.getMovieListNew(searchTerm)

    fun getPageByTerm(searchTerm: String) = pageDao.getPageByTerm(searchTerm)
}