package com.moviez.app.utils

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author adnan
 * @since  10/06/20
 */
internal class DebounceTextListener(lifecycle: Lifecycle,
                                    private val onTextChange: (String?) -> Unit
    ) : SearchView.OnQueryTextListener {

    private var debouncePeriod: Long = 500

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                if(newText.length > 2) {
                    delay(debouncePeriod)
                    onTextChange(newText)
                }
            }
        }
        return false
    }
}