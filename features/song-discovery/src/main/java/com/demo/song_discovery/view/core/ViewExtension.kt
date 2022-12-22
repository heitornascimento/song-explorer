package com.demo.song_discovery.view.core

import android.view.View

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