package com.agnitt.vdt.back.utils

import kotlin.random.Random

fun <T> MutableList<T>.addIfNotNull(item: T?) {
    if (item != null) add(item)
}

fun <T> MutableList<T>.addAllIfNotNull(collection: Collection<T>?): MutableList<T>? {
    if (collection == null) return null
    addAll(collection)
    return this
}

val randomMutableListFloat =
        { size: Int, start: Int, end: Int -> MutableList(size) { Random.nextInt(start, end).toFloat() } }

var randomFloat = { start: Int, end: Int -> Random.nextInt(start, end).toFloat() }

fun MutableList<Long>.getRandomItemsIds(): MutableList<Long> =
        mutableListOf<Long>().apply {
            if (this@getRandomItemsIds.size > 1) {
                val size = Random.nextInt(0, this@getRandomItemsIds.size)
                for (i in 0 until size) {
                    val id = this@getRandomItemsIds[Random.nextInt(0, this@getRandomItemsIds.size)]
                    if (!this.contains(id)) this.add(id)
                }
            } else this.add(this@getRandomItemsIds[0])
        }