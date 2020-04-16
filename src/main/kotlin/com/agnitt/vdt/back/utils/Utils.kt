package com.agnitt.vdt.back.utils

import java.util.*

fun <T> MutableList<T>.addIfNotNull(item: T?) {
    if (item != null) add(item)
}

var count: Long = 0
val uniqueId = { (++count * Random(5).nextFloat()).toInt().toLong() }

