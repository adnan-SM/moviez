package com.moviez.app.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moviez.app.model.MovieSearchResponse
import com.moviez.app.model.Page

/**
 * @author adnan
 * @since  10/06/20
 */
@Dao
interface PageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPages(pages: List<Page>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPage(page: Page)

    @Update
    fun updatePage(page: Page)

    @Query("SELECT latestPage FROM Page WHERE searchTerm = :searchTerm")
    fun getPageByTerm(searchTerm: String): Int

}