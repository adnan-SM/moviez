package com.moviez.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author adnan
 * @since  10/06/20
 */
@Entity
data class Page(@PrimaryKey var searchTerm: String, var latestPage: Int)