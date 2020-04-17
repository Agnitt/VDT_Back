package com.agnitt.vdt.back.utils

fun <T> MutableList<T>.addIfNotNull(item: T?) {
    if (item != null) add(item)
}


