package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = T_SIDE_ITEMS)
data class SideItem(
        @Id @GeneratedValue override val id: Long,
        override val owner: Long,
        val name: String,
        val measure: String,
        val type: String,
        @ElementCollection val dataList: List<Float>,
        var currentValue: Float,
        val connectionItemId: Long
) : SideContentItem() {
    override fun toString() = "\n[SIDE]\nid = $id\nowner = $owner\nname = $name\nmeasure = $measure\ntype = $type\ndataList = $dataList\ncurrentValue = $currentValue"
}