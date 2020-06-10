package com.moviez.app.ui

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviez.app.R
import com.moviez.app.utils.Result.Status
import com.moviez.app.model.Page
import com.moviez.app.utils.DebounceTextListener
import com.moviez.app.utils.hideKeyboard
import com.moviez.app.utils.onScrollToEnd
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieListViewModel
    private val adapter = MoviesAdapter()
    private var currentPageCount = 1
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private var currentSearchTerm = "FRIENDS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        setContentView(R.layout.activity_main)
        supportActionBar?.hide(); //hide the title bar

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MovieListViewModel::class.java]

        mLayoutManager = LinearLayoutManager(this)
        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = mLayoutManager
        rvMovieList.setHasFixedSize(true)
        rvMovieList.onScrollToEnd {
            viewModel.inputSearchData.postValue(Page(currentSearchTerm, ++currentPageCount))
        }

        subscribeToUI()
        //Expand the searchView by default
        search_view.setIconifiedByDefault(false)
        search_view.queryHint = "Search for any Movie/Tv Show here....."
        //Set Debounce listener on searchView
        search_view.setOnQueryTextListener(
            DebounceTextListener(this@MainActivity.lifecycle,
                onTextChange = { newText ->
                    newText?.let {
                        currentPageCount = 1
                        currentSearchTerm = it
                        viewModel.inputSearchData.postValue(Page(it, currentPageCount))
                    }
                })
        )
    }

    private fun subscribeToUI() {
        viewModel.movieLiveData.observe(this, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.INVISIBLE
                    result.data?.let {
                        adapter.setMovieList(it)
                        adapter.notifyDataSetChanged()
                        hideKeyboard(this)
                    }
                }
                Status.LOADING -> progress_bar.visibility = View.VISIBLE
                Status.ERROR -> {
                    progress_bar.visibility = View.INVISIBLE
                    //Snackbar.make(this, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}