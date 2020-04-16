package com.agnitt.vdt.back.models

import javax.persistence.Table

abstract class MainContentItem {
    abstract val id: Long
    abstract val owner: Long

    abstract fun change(sideItemName: String, currentValue:Float): MainContentItem
}

//fun MutableList<MainContentItem>.addIfNotNull(item: MainContentItem?) {
//    if (item != null) add(item)
//}

abstract class SideContentItem {
    abstract val id: Long
    abstract val owner: Long
}

//fun MutableList<SideContentItem>.addIfNotNull(item: SideContentItem?) {
//    if (item != null) add(item)
//}
