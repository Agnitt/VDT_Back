package com.agnitt.vdt.back.data

import com.agnitt.vdt.back.models.Chart
import com.agnitt.vdt.back.models.PageModel
import com.agnitt.vdt.back.models.SideItem
import com.agnitt.vdt.back.models.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PageRepository : CrudRepository<PageModel, Long>, JpaRepository<PageModel, Long>

@Repository
interface ChartRepository : CrudRepository<Chart, Long>, JpaRepository<Chart, Long> {
//    override fun <S : Chart?> saveAndFlush(entity: S): S {
//        val newEntity = entity?.copy() ?: return save(entity)
//        newEntity.apply {
//            basicDataList = null
//            modelDataList = null
//        }
//        val thisEntity = save(newEntity).apply {
//
//        }
//        return save(thisEntity)
//    }
}

@Repository
interface TableRepository : CrudRepository<Table, Long>, JpaRepository<Table, Long> {
//    override fun <S : Table?> saveAndFlush(entity: S): S {
//        val newEntity = entity?.apply {
//
//        }
//        var thisEntity = save(newEntity).apply {
//            dataList =
//        }
//        return save(thisEntity)
//    }
}

@Repository
interface SideItemRepository : CrudRepository<SideItem, Long>, JpaRepository<SideItem, Long> {
//    override fun <S : SideItem?> saveAndFlush(entity: S): S {
//        val newEntity = entity?.apply {
//
//        }
//        var thisEntity = save(newEntity).apply {
//            dataList =
//        }
//        return save(thisEntity)
//    }
}