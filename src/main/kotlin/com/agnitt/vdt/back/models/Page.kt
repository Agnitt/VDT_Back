package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.data.PageService
import com.agnitt.vdt.back.utils.*
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = T_PAGES)
data class PageModel(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long,
        val name: String,
        val type: String,
        @ElementCollection
        var mainItemsIds: MutableList<Long>,
        @ElementCollection
        var sideItemsIds: MutableList<Long>
) {
    fun toPage(service: PageService) = Page(id, name, type,
            mainItems = mutableListOf<MainContentItem>().apply {
                mainItemsIds.forEach { id ->
                    this@apply.addIfNotNull(when (type) {
                        "chart" -> service.getById<Chart>(id)
                        "table" -> service.getById<com.agnitt.vdt.back.models.Table>(id)
                        else -> null
                    })
                }
            },
            sideItems = mutableListOf<SideContentItem>().apply {
                sideItemsIds.forEach { id -> this@apply.addIfNotNull(service.getById<SideItem>(id)) }
            }
    )
}

data class Page(
        val id: Long,
        val name: String,
        val type: String,
        var mainItems: List<MainContentItem>,
        var sideItems: List<SideContentItem>
) {
    override fun toString() =
            "\n[PAGE_MOD]\nid = $id\nname = $name\ntype = $type\nmainItemsIds = $mainItems\nsideItems = $sideItems"
}