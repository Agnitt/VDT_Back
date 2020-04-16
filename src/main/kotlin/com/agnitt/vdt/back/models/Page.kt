package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = T_PAGES)
class PageModel(
        @Id @GeneratedValue val id: Long,
        val name: String,
        val type: String,
        @ElementCollection
        var mainItemsIds: List<Long>,
        @ElementCollection
        var sideItemsIds: List<Long>
)

/**

fun getPage(pageId: Long) = dao.getPageById(pageId).run{
return@run Page(this@run.id, this@run.name, this@run.type,
mainItems = mutableListOf<MainContentItem>().apply {
this@run.mainItemsIds.forEach {
if (this@run.type == "chart") this@apply.add(dao.getChartById(it))
else if (this@run.type == "table") this@apply.add(dao.getTableById(it))
}
},
sideItems = mutableListOf<SideContentItem>().apply {
this@run.sideItemsIds.forEach { this@apply.add(dao.getSideItemById(it)) }
}
)}

 **/

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

//    inline fun <reified T> getById(id: Long): T? = when (T::class) {
//        Chart::class, Table::class -> mainItems.run {
//            forEach { if (it.id == id) return@run it as T }
//            return@run null
//        }
//        SideItem::class -> sideItems.run {
//            forEach { if (it.id == id) return@run it as T }
//            return@run null
//        }
//        else -> null
//    }
//
//    inline fun <reified T> getByOwner(owner: Long): List<T>? = mutableListOf<T>().apply {
//        when (T::class) {
//            Chart::class, Table::class -> mainItems.forEach { if (it.owner == owner) add(it as T) }
//            SideItem::class -> sideItems.forEach { if (it.owner == owner) add(it as T) }
//            else -> return@apply
//        }
//    }

