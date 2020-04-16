package com.agnitt.vdt.back.data

import com.agnitt.vdt.back.models.*
import com.agnitt.vdt.back.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PageService {
    @Autowired
    var pageRepo: PageRepository? = null

    @Autowired
    var chartRepo: ChartRepository? = null

    @Autowired
    var tableRepo: TableRepository? = null

    @Autowired
    var sideItemRepo: SideItemRepository? = null

    /** get **/

    fun getAllPages(): List<PageModel> = arrayListOf<PageModel>().apply { pageRepo?.findAll()?.forEach { add(it) } }
    fun getPageModelById(id: Long): PageModel? = pageRepo?.findByIdOrNull(id)
    fun getPageById(pageId: Long): Page? = getPageModelById(pageId)?.let {
        Page(it.id, it.name, it.type,
                mainItems = mutableListOf<MainContentItem>().apply {
                    it.mainItemsIds.forEach { id ->
                        this@apply.addIfNotNull(when (it.type) {
                            "chart" -> getChartById(id)
                            "table" -> getTableById(id)
                            else -> null
                        })
                    }
                },
                sideItems = mutableListOf<SideContentItem>().apply {
                    it.sideItemsIds.forEach { id -> this@apply.addIfNotNull(getSideItemById(id)) }
                }
        )
    }

    fun getChartById(id: Long): Chart? = chartRepo?.findByIdOrNull(id)
    fun getChartsByOwner(owner: Long): List<Chart>? = getPageById(owner)?.mainItems?.filterIsInstance<Chart>()

    fun getTableById(id: Long): Table? = tableRepo?.findByIdOrNull(id)
    fun getTablesByOwner(owner: Long): List<Table>? = getPageById(owner)?.mainItems?.filterIsInstance<Table>()

    fun getSideItemById(id: Long): SideItem? = sideItemRepo?.findByIdOrNull(id)
    fun getSideItemsByOwner(owner: Long): List<SideItem>? = getPageById(owner)?.sideItems?.filterIsInstance<SideItem>()

    fun getConnectionItemAfterChange(sideItemId: Long, currentValue: Float): MainContentItem? {
        val sideItem = getSideItemById(sideItemId) ?: return null
        sideItem.currentValue = currentValue
        update(sideItem)
        val connectionItemId = sideItem.connectionItemId
        var connectionItem = getChartById(connectionItemId) ?: getTableById(connectionItemId) ?: return null
        return connectionItem.change(sideItem.name, currentValue)
    }

    /** insert **/

    fun insert(page: PageModel): Long {
        pageRepo?.save(page)
        return page.id
    }

    fun insert(vararg pages: PageModel): List<Long> = mutableListOf<Long>().apply {
        pages.forEach {
            pageRepo?.save(it)
            this.add(it.id)
        }
    }

    fun insert(chart: Chart): Long {
        chartRepo?.save(chart)
        return chart.id
    }

    fun insert(vararg charts: Chart): List<Long> = mutableListOf<Long>().apply {
        charts.forEach {
            chartRepo?.save(it)
            this.add(it.id)
        }
    }

    fun insert(table: Table): Long {
        tableRepo?.save(table)
        return table.id
    }

    fun insert(vararg tables: Table): List<Long> = mutableListOf<Long>().apply {
        tables.forEach {
            tableRepo?.save(it)
            this.add(it.id)
        }
    }

    fun insert(sideItem: SideItem): Long {
        sideItemRepo?.save(sideItem)
        return sideItem.id
    }

    fun insert(vararg sideItems: SideItem): List<Long> = mutableListOf<Long>().apply {
        sideItems.forEach {
            sideItemRepo?.save(it)
            this.add(it.id)
        }
    }

    /** update **/
    fun update(pageModel: PageModel): Boolean = pageRepo?.let {
        if (it.existsById(pageModel.id)) it.deleteById(pageModel.id)
        it.save(pageModel)
        it.existsById(pageModel.id)
    } ?: false

    fun update(chart: Chart): Boolean = chartRepo?.let {
        if (it.existsById(chart.id)) it.deleteById(chart.id)
        it.save(chart)
        it.existsById(chart.id)
    } ?: false

    fun update(table: Table): Boolean = tableRepo?.let {
        if (it.existsById(table.id)) it.deleteById(table.id)
        it.save(table)
        it.existsById(table.id)
    } ?: false

    fun update(item: SideItem): Boolean = sideItemRepo?.let {
        if (it.existsById(item.id)) it.deleteById(item.id)
        it.save(item)
        it.existsById(item.id)
    } ?: false

    /** delete **/

    fun delete(pageModel: PageModel): Boolean = pageModel.let { pm ->
        val type = pm.type
        pm.mainItemsIds.forEach { if (type == "chart") deleteChartById(it) else if (type == "table") deleteTableById(it) }
        pm.sideItemsIds.forEach { deleteSideItemById(it) }
        deletePageModelById(pm.id)
    }

    fun deletePageModelById(id: Long): Boolean = pageRepo?.let {
        it.deleteById(id)
        !it.existsById(id)
    } ?: false

    fun delete(chart: Chart): Boolean = deleteChartById(chart.id)
    fun deleteChartById(id: Long): Boolean = chartRepo?.let {
        it.deleteById(id)
        !it.existsById(id)
    } ?: false

    fun delete(table: Table): Boolean = deleteTableById(table.id)
    fun deleteTableById(id: Long): Boolean = tableRepo?.let {
        it.deleteById(id)
        !it.existsById(id)
    } ?: false

    fun delete(item: SideItem): Boolean = deleteSideItemById(item.id)
    fun deleteSideItemById(id: Long): Boolean = sideItemRepo?.let {
        it.deleteById(id)
        !it.existsById(id)
    } ?: false

    /** usefull funs **/

}