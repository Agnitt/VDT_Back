package com.agnitt.vdt.back.data

import com.agnitt.vdt.back.models.*
import com.agnitt.vdt.back.utils.getRandomItemsIds
import com.agnitt.vdt.back.utils.randomFloat
import com.agnitt.vdt.back.utils.randomMutableListFloat

lateinit var service: PageService

fun PageService.fill() {
    service = this
    val pMain = PageModel(0, "Ключевые факторы", Types.CHART.name, mutableListOf(), mutableListOf())
    val pOpex = PageModel(0, "ОРЕХ", Types.CHART.name, mutableListOf(), mutableListOf())
    val pMacro = PageModel(0, "Макро и ЦБ", Types.CHART.name, mutableListOf(), mutableListOf())
    val pEco = PageModel(0, "Экосистема", Types.CHART.name, mutableListOf(), mutableListOf())
    val pSens = PageModel(0, "Чувствительность", Types.TABLE.name, mutableListOf(), mutableListOf())

    insert(pMain, pOpex, pMacro, pEco, pSens)

    pMain.initMain()
    pOpex.initOpex()
    pMacro.initMacro()
    pEco.initEco()
    pSens.initSens()
}

fun PageModel.initMain() = this.apply {
    fillMainPart(
            initChart("Чистая прибыль", "млрд.руб", Types.BIG.name),
            initChart("EPS", "руб", Types.SMALL.name),
            initChart("ROE", "%", Types.SMALL.name),
            initChart("COR", "%", Types.SMALL.name),
            initChart("CIR", "%", Types.SMALL.name),
            initChart("ЧКД/OPEX", "%", Types.SMALL.name),
            initChart("CAR Basel III", "%", Types.SMALL.name)
    )
    fillSidePart(
            initSideItem(text_main_factors_seekBar1, Types.DISCRETE_SLIDER.name, mutableListOf(45f, 65f)),
            initSideItem(text_main_factors_switch1, Types.SWITCH.name, mutableListOf(0f, 1f), 0),
            initSideItem(text_main_factors_seekBar2, Types.DISCRETE_SLIDER.name, mutableListOf(11f, 15f)),
            initSideItem(text_main_factors_seekBar3, Types.SWITCH_SLIDER.name, mutableListOf(0.15f, 0.2f), 0),
            initSideItem(text_main_factors_popMenuNim, Types.POPUP.name, mutableListOf()),
            initSideItem(text_main_factors_seekBar1, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(45f, 65f)),
            initSideItem(text_main_factors_switch1, Types.POPUP_SWITCH.name, mutableListOf(0f, 1f), 0)
    )
}

fun PageModel.initOpex() = this.apply {
    fillMainPart(
            initChart("OPEX", "млрд.руб", Types.BIG.name),
            initChart("Персонал", "млрд.руб", Types.SMALL.name),
            initChart("IT", "млрд.руб", Types.SMALL.name),
            initChart("Недвижимость", "млрд.руб", Types.SMALL.name),
            initChart("Бизнес-расходы", "млрд.руб", Types.SMALL.name),
            initChart("Маркетинг", "млрд.руб", Types.SMALL.name),
            initChart("Численность", "тыс.чел.", Types.SMALL.name)
    )
    fillSidePart(
            initSideItem(text_opex_popMenu, Types.POPUP.name, mutableListOf()),
            initSideItem(text_opex_seekBar1, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(-5f, 0f), 5),
            initSideItem(text_opex_seekBar2, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(60.3f, 64.3f)),
            initSideItem(text_opex_seekBar3, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(4f, 10f), 3)
    )
}

fun PageModel.initMacro() = this.apply {
    fillMainPart(
            initChart("Чистая прибыль", "млрд.руб", Types.BIG.name),
            initChart("EPS", "руб", Types.SMALL.name),
            initChart("ROE", "%", Types.SMALL.name),
            initChart("COR", "%", Types.SMALL.name),
            initChart("CIR", "%", Types.SMALL.name),
            initChart("ЧКД/OPEX", "%", Types.SMALL.name),
            initChart("CAR Basel III", "%", Types.SMALL.name)
    )
    fillSidePart(
            initSideItem(text_macro_cb_popMenuUSD, Types.POPUP.name, mutableListOf()),
            initSideItem(text_macro_cb_popMenuUSD, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(60.5f, 80.5f)),
            initSideItem(text_macro_cb_popMenuUSD, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(61f, 81f)),
            initSideItem(text_macro_cb_popMenuUSD, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(61.5f, 81.5f)),
            initSideItem(text_macro_cb_popMenuUSD, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(62f, 82f))
    )
}

fun PageModel.initEco() = this.apply {
    fillMainPart(
            initChart("Чистая прибыль", "млрд.руб", Types.BIG.name),
            initChart("EPS", "руб", Types.SMALL.name),
            initChart("ROE", "%", Types.SMALL.name),
            initChart("CAR Basel III", "%", Types.SMALL.name),
            initChart("CIR", "%", Types.SMALL.name),
            initChart("ЧКД/OPEX", "%", Types.SMALL.name),
            initChart("Инвест. в Экосистему", "млрд.руб", Types.SMALL.name)
    )
    fillSidePart(
            initSideItem(text_ecosystem_popMenu, Types.POPUP.name, mutableListOf()),
            initSideItem(text_ecosystem_popMenu, Types.POPUP_DISCRETE_SLIDER.name, mutableListOf(27.6f, 133.6f))
    )
}

fun PageModel.initSens() = this.apply {
    fillMainPart(initTable())
    fillSidePart(
            initSideItem(text_sens_nameMenu, Types.TEXT_VIEW.name, mutableListOf()),
            initSideItem(text_sens_radioGroup1, Types.RADIO_GROUP.name, mutableListOf(12.0f, 14.0f, 16.0f, 18.0f), 2),
            initSideItem(text_sens_radioGroup2, Types.RADIO_GROUP.name, mutableListOf(4.9f, 5.1f, 5.2f), 2),
            initSideItem(text_sens_radioGroup4, Types.RADIO_GROUP.name, mutableListOf(1.0f, 1.1f, 1.2f, 1.3f, 1.4f), 3),
            initSideItem(text_sens_radioGroup3, Types.RADIO_GROUP.name, mutableListOf(31.1f, 32.1f, 33.1f, 34.1f), 3)
    )
}

fun PageModel.fillMainPart(vararg items: MainContentItem): PageModel? {
    val ownerId = this.id
    items.forEach {
        it.owner = ownerId
        when (it) {
            is Chart -> it.apply {
                it.basicDataList = randomMutableListFloat(5, 20, 132)
                it.modelDataList = (it.basicDataList!!.map { num -> num + randomFloat(10, 20) }).toMutableList()
                it.strategyData = randomFloat(30, 140)
//                temp.update(it.id, 0f)
            }
            is Table -> {
                it.dataList = randomMutableListFloat(12 * 20, 983, 1012)
//                temp.update(it.id, 0f)
            }
            else -> return@forEach
        }
        service.insert(it)
        this.mainItemsIds?.add(it.id)
    }
    return service.update(this)
}

fun PageModel.fillSidePart(vararg items: SideContentItem) {
    val ownerId = this.id
    items.forEach {
        it.owner = ownerId
        if (it is SideItem) {
            it.currentValue = if (it.dataList.size > 0) it.dataList[0] else 0f
            it.relatedItemId = this.mainItemsIds?.getRandomItemsIds()
//            temp.update(it.id, it.currentValue)
        }
        service.insert(it)
        this.sideItemsIds?.add(it.id)
    }
    service.update(this)
}
