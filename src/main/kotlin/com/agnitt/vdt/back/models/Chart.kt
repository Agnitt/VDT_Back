package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.data.TemporaryStorage.Companion.temp
import com.agnitt.vdt.back.utils.T_CHARTS
import com.agnitt.vdt.back.utils.addAllIfNotNull
import javax.persistence.*
import javax.persistence.Table

@PersistenceContext(unitName = "...")
private val entityManager: EntityManager? = null

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
        val factor = temp.get(id, 0f) + currentValue
        val newModelDataList = mutableListOf<Float>()
                .addAllIfNotNull(modelDataList)
                ?.map { it + factor * 1.5f }
                ?.toMutableList()
        val newBasicDataList = mutableListOf<Float>()
                .addAllIfNotNull(basicDataList)
                ?.map { it + factor * 1.2f }
                ?.toMutableList()
        val newStrategyData = strategyData + factor * 2.3f
        temp.update(id, factor)
        return Chart(id, owner, name, measure, type, newBasicDataList, newModelDataList, newStrategyData)
    }

    override fun toString() = "\n[CHART]\nchartId = $id\nownerId = $owner\nname = $name\nmeasure = $measure\ntype = $type\nbasicDataList = $basicDataList\nmodelDataList = $modelDataList\nstrategyData = $strategyData"
}

fun initChart(name: String, measure: String, type: String) =
        Chart(0, 0, name, measure, type, null, null, 0f)
