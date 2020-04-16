package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = T_TABLES)
data class Table(
        @Id @GeneratedValue override val id: Long,
        override val owner: Long,
        @ElementCollection var dataList: List<Float>
) : MainContentItem() {

    override fun change(sideItemName: String, currentValue: Float): com.agnitt.vdt.back.models.Table {
        val newList = mutableListOf<Float>()
        dataList.forEach { newList.add(it + currentValue * 5) }
        dataList = newList
        return this
    }

    override fun toString() = "\n[TABLE]\nid = $id\nowner = $owner\ndataList = $dataList"
}
