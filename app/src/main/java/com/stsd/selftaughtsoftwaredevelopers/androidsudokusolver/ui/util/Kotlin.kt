package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util

inline fun <reified T> Iterable<T>.chunked(size: Int): List<Array<T>> {
    return windowed(size, size, partialWindows = true).map {
        it.toTypedArray()
    }
}
