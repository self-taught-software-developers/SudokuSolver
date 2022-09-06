package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util

fun <T> MutableList<T>.takeTopOrNull(value: T) : T? {
    return if(value == lastOrNull()) {
        removeLastOrNull()
        lastOrNull()
    } else {
        lastOrNull()
    }.also { removeLastOrNull() }
}
fun <T> MutableList<T>.top(value: T) {
    if (this.contains(value)) {
        this.remove(value)
        this.add(value)
    } else this.add(value)
}

inline fun <reified T> Iterable<T>.chunked(size: Int): List<Array<T>> {
    return windowed(size, size, partialWindows = true).map {
        it.toTypedArray()
    }
}

inline fun <reified T> Array<T>.copy(
    function: (Array<T>) -> Unit,
) = this.apply(function).clone()

fun Int?.greaterThanOne() : Boolean {
    return this?.let {
        it > 1
    } ?: false
}
