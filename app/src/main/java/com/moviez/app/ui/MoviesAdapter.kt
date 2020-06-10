package com.moviez.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviez.app.R
import com.moviez.app.model.MovieSearchResponse.Movie
import com.squareup.picasso.Picasso


class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    private var movies: List<Movie> = listOf()

     fun setMovieList(movieList: List<Movie>) {
        movies = movieList
    }

    inner class ItemViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val movieTitle = listItemView.findViewById<TextView>(R.id.media_title)
        val releaseYear = listItemView.findViewById<TextView>(R.id.media_year)
        val movieImage = listItemView.findViewById<ImageView>(R.id.media_image)
        val mediaType = listItemView.findViewById<TextView>(R.id.media_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.movie_row_item, parent, false)
        return ItemViewHolder(movieView)
    }

    override fun getItemCount() = movies.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = movies[position]
        var mediaType = "Movie"
        Picasso.get().load(movie.poster).into(holder.movieImage)
        holder.movieTitle.text = movie.title
        holder.releaseYear.text = movie.year
        if (!movie.type.equals("movie", true)) {
            mediaType = "TV Series"
        }
        holder.mediaType.text = mediaType
    }

}