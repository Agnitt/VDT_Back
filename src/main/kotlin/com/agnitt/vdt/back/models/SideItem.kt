package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = T_SIDE_ITEMS)
data class SideItem(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) override val id: Long,
        override val owner: Long,
        val name: String,
        val measure: String,
        val type: String,
        @ElementCollection val dataList: MutableList<Float>,
        var currentValue: Float,
        @ElementCollection val relatedItemId: MutableList<Long>
) : SideContentItem() {
    override fun toString() = "\n[SIDE]\nid = $id\nowner = $owner\nname = $name\nmeasure = $measure\ntype = $type\ndataList = $dataList\ncurrentValue = $currentValue"
}