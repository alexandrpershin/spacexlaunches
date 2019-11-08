package com.alexandrpershin.spacexlaunches.utils

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

fun MenuItem?.makeGone() {
    this?.isVisible = false
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.disableClick() {
    isFocusable = false
    isClickable = false
}

fun View.enableClick() {
    isFocusable = true
    isClickable = true
}