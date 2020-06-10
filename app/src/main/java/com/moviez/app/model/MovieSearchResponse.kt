package com.moviez.app.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("Response")
    val response: String, // True
    @SerializedName("Search")
    val search: List<Movie>?,
    @SerializedName("totalResults")
    val totalResults: String // 1735
) {
    @Entity
    data class Movie (
        @PrimaryKey @SerializedName("imdbID")
        val id: String, // tt0108778
        @SerializedName("Poster")
        val poster: String, // https://m.media-amazon.com/images/M/MV5BNDVkYjU0MzctMWRmZi00NTkxLTgwZWEtOWVhYjZlYjllYmU4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SX300.jpg
        @SerializedName("Title")
        var title: String, // Friends
        @SerializedName("Type")
        val type: String, // series
        @SerializedName("Year")
        val year: String, // 1994–2004
        var searchTerm: String?
    )
}