package com.agnitt.vdt.back.models

abstract class MainContentItem {
    abstract val id: Long
    abstract var owner: Long

    abstract fun change(sideItemName: String, currentValue:Float): MainContentItem
}

abstract class SideContentItem {
    abstract val id: Long
    abstract var owner: Long
}