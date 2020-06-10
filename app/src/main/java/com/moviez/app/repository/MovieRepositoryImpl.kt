package com.moviez.app.repository

import androidx.lifecycle.LiveData
import com.moviez.app.api.LocalDataSource
import com.moviez.app.api.RemoteDataSource
import com.moviez.app.model.MovieSearchResponse.Movie
import com.moviez.app.utils.Result
import com.moviez.app.utils.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

/**
 * @author adnan
 * @since  09/06/20
 */
@Singleton
class MovieRepositoryImpl @Inject constructor (private val localDataSource: LocalDataSource,
                                               private val remoteDataSource: RemoteDataSource
): MovieRepository {

    override fun getMovieList(searchTerm: String, page: Int): LiveData<Result<List<Movie>>> {

        var pageNumber = page
        return resultLiveData(
            dbQuery = {
                var pageCountInDB = localDataSource.getPageByTerm(searchTerm)
                pageNumber = max(pageNumber, ++pageCountInDB)
                localDataSource.getMovies(searchTerm)
            },
            ioRequest = {
                remoteDataSource.getMovies(searchTerm, pageNumber)
            },
            postRequestOperation = {
                it.search?.let { movieList ->
                    movieList.forEach { movie ->
                        movie.searchTerm = searchTerm
                    }
                    localDataSource.updateSearchPage(searchTerm, pageNumber)
                    localDataSource.saveMovieList(searchTerm, it.search)
                }
            }
        )
    }
}