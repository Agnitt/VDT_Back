package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = T_CHARTS)
data class Chart(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) override val id: Long,
        override var owner: Long,
        val name: String,
        val measure: String,
        val type: String,
        @ElementCollection var basicDataList: MutableList<Float>?,
        @ElementCollection var modelDataList: MutableList<Float>?,
        var strategyData: Float
) : MainContentItem() {

    override fun change(sideItemName: String, currentValue: Float): Chart {
        val newList = mutableListOf<Float>()
        modelDataList?.forEach { newList.add(it + currentValue * 10) }
        modelDataList = newList
        strategyData += currentValue * 10
        return this
    }

    override fun toString() = "\n[CHART]\nchartId = $id\nownerId = $owner\nname = $name\nmeasure = $measure\ntype = $type\nbasicDataList = $basicDataList\nmodelDataList = $modelDataList\nstrategyData = $strategyData"
}

fun initChart(name: String, measure: String, type: String) =
        Chart(0, 0, name, measure, type, null, null, 0f)
