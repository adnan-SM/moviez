package com.moviez.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviez.app.model.MovieSearchResponse
import com.moviez.app.model.Page

/**
 * @author adnan
 * @since  09/06/20
 */
@Database(entities = [MovieSearchResponse.Movie::class, Page::class], version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun pageDao(): PageDao
}