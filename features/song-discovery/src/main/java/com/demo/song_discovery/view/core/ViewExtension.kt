package com.demo.song_discovery.view.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hideShrink() : View {
    if (visibility != View.GONE || visibility != View.INVISIBLE ) {
        visibility = View.GONE
    }
    return this
}

fun View.hide() : View {
    if (visibility != View.GONE || visibility != View.INVISIBLE ) {
        visibility = View.INVISIBLE
    }
    return this
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}