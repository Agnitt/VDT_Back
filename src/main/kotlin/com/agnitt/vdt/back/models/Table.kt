package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.data.TemporaryStorage.Companion.temp
import com.agnitt.vdt.back.utils.T_TABLES
import javax.persistence.*
import javax.persistence.Table

typealias mTable = com.agnitt.vdt.back.models.Table

@Entity
@Table(name = T_TABLES)
data class Table(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) override val id: Long,
        override var owner: Long,
        @ElementCollection var dataList: MutableList<Float>?
) : MainContentItem() {
    override fun change(sideItemName: String, currentValue: Float): mTable {
        val newList = mutableListOf<Float>()
        val factor = temp.get(id, 0f)
        dataList?.forEach { newList.add(it + factor + currentValue * 5) }
        temp.update(id, factor + currentValue * 5)
        return mTable(id, owner, newList)
    }

    override fun toString() = "\n[TABLE]\nid = $id\nowner = $owner\ndataList = $dataList"
}

fun initTable() = Table(0, 0, null)