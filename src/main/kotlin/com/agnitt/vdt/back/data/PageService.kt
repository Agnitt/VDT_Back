package com.agnitt.vdt.back.data

import com.agnitt.vdt.back.models.*
import com.agnitt.vdt.back.utils.addIfNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PageService {
    @Autowired
    lateinit var pageRepo: PageRepository

    @Autowired
    lateinit var chartRepo: ChartRepository

    @Autowired
    lateinit var tableRepo: TableRepository

    @Autowired
    lateinit var sideItemRepo: SideItemRepository

    /** get **/
    fun getAllPages(): MutableList<Page> = mutableListOf<Page>().apply {
        pageRepo.findAll().forEach { addIfNotNull(it.toPage(this@PageService)) }
    }

    fun getAllPageModels(): MutableList<PageModel> = mutableListOf<PageModel>().apply {
        pageRepo.findAll().forEach { add(it) }
    }

    final inline fun <reified T> getById(id: Long): T? = when (T::class) {
        PageModel::class -> pageRepo.findByIdOrNull(id) as T?
        Chart::class -> chartRepo.findByIdOrNull(id) as T?
        Table::class -> tableRepo.findByIdOrNull(id) as T?
        SideItem::class -> sideItemRepo.findByIdOrNull(id) as T?
        Page::class -> (pageRepo.findByIdOrNull(id))?.toPage(this) as T?
        else -> null
    }

    final inline fun <reified T> getByOwner(owner: Long): MutableList<T>? = getById<Page>(owner)?.let {
        when (T::class) {
            Chart::class, Table::class -> it.mainItems
            SideItem::class -> it.sideItems
            else -> null
        }
    }?.filterIsInstance<T>()?.toMutableList()

    fun getRelatedItemsAfterChange(sideItemId: Long, currentValue: Float): MutableList<MainContentItem>? {
        val sideItem = getById<SideItem>(sideItemId)?.apply {
            this.currentValue = currentValue
            update(this)
        } ?: return null
        val relatedItemIds = sideItem.relatedItemId
        val sideItemName = sideItem.name

        return mutableListOf<MainContentItem>().apply {
            relatedItemIds.forEach {
                val relatedItem = getById<Chart>(it) ?: getById<Table>(it)
                this.addIfNotNull(relatedItem?.change(sideItemName, currentValue))
            }
        }
    }

    /** insert **/
    @Transactional
    final inline fun <reified T> insert(item: T): Long = when (item) {
        is PageModel -> pageRepo.save(item).id
        is Chart -> chartRepo.save(item).id
        is Table -> tableRepo.save(item).id
        is SideItem -> sideItemRepo.save(item).id
        else -> null
    } ?: 0

    @Transactional
    final inline fun <reified T> insert(vararg items: T): MutableList<Long> = mutableListOf<Long>().apply {
        items.forEach { addIfNotNull(insert(it)) }
    }

    /** update **/
    @Transactional
    final inline fun <reified T> update(item: T) = when (item) {
        is PageModel -> pageRepo.save(item)
        is Chart -> chartRepo.save(item)
        is Table -> tableRepo.save(item)
        is SideItem -> sideItemRepo.save(item)
        else -> null
    } as T?

    @Transactional
    final inline fun <reified T> update(vararg items: T) = items.forEach { update(it) }

    /** delete **/

    fun deleteAllPages(): String {
        var isComplete = true
        listOf(pageRepo, chartRepo, tableRepo, sideItemRepo).forEach { repo ->
            repo.deleteAll()
            if (repo.count() != 0.toLong()) isComplete = false
        }
        return if (isComplete) "Mission complete" else "Mission failed"
    }

    final inline fun <reified T> delete(item: T) = when (item) {
        is Chart -> chartRepo.delete(item)
        is Table -> tableRepo.delete(item)
        is SideItem -> sideItemRepo.delete(item)
        is PageModel -> (item as PageModel).let { pm ->
            val type = pm.type
            pm.mainItemsIds.forEach { if (type == "chart") deleteById<Chart>(it) else if (type == "table") deleteById<Table>(it) }
            pm.sideItemsIds.forEach { deleteById<SideItem>(it) }
            pageRepo.delete(pm)
        }
        else -> null
    }

    final inline fun <reified T> deleteById(id: Long) = when (T::class) {
        PageModel::class -> pageRepo
        Chart::class -> chartRepo
        Table::class -> tableRepo
        SideItem::class -> sideItemRepo
        else -> null
    }?.deleteById(id)



}