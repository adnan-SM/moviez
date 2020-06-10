package com.moviez.app.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers

/**
 * @author adnan
 * @since  09/06/20
 */
fun <X, Y> resultLiveData(dbQuery: () -> LiveData<X>,
                          ioRequest: suspend () -> Result<Y>,
                          postRequestOperation: suspend (Y) -> Unit): LiveData<Result<X>> {

    return liveData(Dispatchers.IO) {
        emit(Result.loading<X>())
        val source = dbQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val responseStatus = ioRequest.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            postRequestOperation(responseStatus.data!!)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error<X>(responseStatus.message!!))
            emitSource(source)
        }
    }
}

fun RecyclerView.onScrollToEnd(
    onScrollNearEnd: (Unit) -> Unit
) = addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!recyclerView.canScrollVertically(1)) {
            onScrollNearEnd(Unit)
        }
    }
})

fun Context.hideKeyboard(activity: Activity) {
    val imm: InputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

