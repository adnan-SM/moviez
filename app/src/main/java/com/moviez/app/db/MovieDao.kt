package com.moviez.app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moviez.app.model.MovieSearchResponse.Movie

@Dao
interface MovieDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieList(movies: List<Movie>)

  @Update
  fun updateMovie(movie: Movie)

  @Query("SELECT * FROM Movie WHERE id = :id_")
  fun getMovie(id_: Int): LiveData<Movie>

  @Query("SELECT * FROM Movie where searchTerm = :searchTerm")
  fun getMovieList(searchTerm: String): LiveData<List<Movie>>

  @Query("SELECT * FROM Movie where searchTerm = :searchTerm")
  fun getMovieListNew(searchTerm: String): List<Movie>

  @Query("SELECT * FROM Movie")
  fun getAllMovies(): List<Movie>

}
