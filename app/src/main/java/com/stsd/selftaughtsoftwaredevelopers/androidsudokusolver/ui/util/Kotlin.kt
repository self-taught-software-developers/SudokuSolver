package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util

import java.util.*

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

//fun <T> MutableStateFlow<T>.updateCopy(function: (T) -> Unit) {
//    when(val array = this.value) {
//        is Array<*> -> this.update {
//            array.copy { function(array as T) } as T
//        }
//    }
//}
//
//fun <T> Array<T>.copy(function: (Array<T>) -> Unit) = this
//    .copyOf().apply { function(this@copy) }

inline fun <reified T> Iterable<T>.chunked(size: Int): List<Array<T>> {
    return windowed(size, size, partialWindows = true).map {
        it.toTypedArray()
    }
}

inline fun <reified T> Array<T>.copy(
    function: (Array<T>) -> Unit,
) = this.apply(function).clone()


fun <T> List<T>.plane(validator: (T) -> Unit) : Boolean {
//        println(distinctBy { validator(it) }.count())
//        println(size)
        return size == distinctBy { validator(it) }.count()
}
