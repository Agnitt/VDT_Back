package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.*
import javax.persistence.Table

typealias mTable = com.agnitt.vdt.back.models.Table

@Entity
@Table(name = T_TABLES)
data class Table(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) override val id: Long,
        override val owner: Long,
        @ElementCollection var dataList: MutableList<Float>
) : MainContentItem() {

    override fun change(sideItemName: String, currentValue: Float): mTable {
        val newList = mutableListOf<Float>()
        dataList.forEach { newList.add(it + currentValue * 5) }
        dataList = newList
        return this
    }

    override fun toString() = "\n[TABLE]\nid = $id\nowner = $owner\ndataList = $dataList"
}
