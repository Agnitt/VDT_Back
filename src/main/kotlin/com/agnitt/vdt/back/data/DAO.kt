package com.agnitt.vdt.back.data

import com.agnitt.vdt.back.models.Chart
import com.agnitt.vdt.back.models.PageModel
import com.agnitt.vdt.back.models.SideItem
import com.agnitt.vdt.back.models.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PageRepository : CrudRepository<PageModel, Long>

@Repository
interface ChartRepository : CrudRepository<Chart, Long>

@Repository
interface TableRepository : CrudRepository<Table, Long>

@Repository
interface SideItemRepository : CrudRepository<SideItem, Long>
