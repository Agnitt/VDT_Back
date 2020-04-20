package com.agnitt.vdt.back.models

import com.agnitt.vdt.back.utils.*
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = T_SIDE_ITEMS)
data class SideItem(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) override val id: Long,
        override var owner: Long,
        val name: String,
        val type: String,
        @ElementCollection val dataList: MutableList<Float>,
        var sectionCount: Int,
        var currentValue: Float,
        @ElementCollection var relatedItemId: MutableList<Long>?
) : SideContentItem() {
    override fun toString() = "\n[SIDE]\nid = $id\nowner = $owner\nname = $name\ntype = $type\ndataList = $dataList\nsizeDataList = $sectionCount\ncurrentValue = $currentValue"
}

fun initSideItem(name: String, type: String, dataList: MutableList<Float>, sectionCount: Int = 4) =
        SideItem(0, 0, name, type, dataList, sectionCount, 0f, null)

/** const name side items **/
const val text_main_factors_seekBar1 = "Доля дивидендов"
const val text_main_factors_seekBar2 = "Темп прироста ЧКД, %"
const val text_main_factors_seekBar3 = "Ставка отчислений в АСВ, %"
const val text_main_factors_switch1 = "Продажа СБЕ"
const val text_main_factors_popMenuNim = "NIM (Чистая процентная маржа), %"
const val text_main_factors_popMenuCor = "COR (Стоимость риска), %"

const val text_opex_popMenu = "Индексация РОТ"
const val text_opex_seekBar1 = "Доп. ежегодное сокращение числонности, %"
const val text_opex_seekBar2 = "Резерв на годовую премию, млрд. руб"
const val text_opex_seekBar3 = "Рост тарифов (недвижимость), %"

const val text_macro_cb_popMenuUSD = "Курс USD"

const val text_ecosystem_popMenu = "Инвестиции в Экосистему"

const val text_sens_nameMenu = "Матрица эластичности"
const val text_sens_radioGroup1 = "CAGR ЧКД, %"
const val text_sens_radioGroup2 = "NIM, %"
const val text_sens_radioGroup3 = "CIR, %"
const val text_sens_radioGroup4 = "COR, %"