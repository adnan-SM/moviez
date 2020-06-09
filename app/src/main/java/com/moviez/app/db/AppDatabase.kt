package com.moviez.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviez.app.model.Movie

/**
 * @author adnan
 * @since  09/06/20
 */
@Database(entities = [Movie::class], version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}