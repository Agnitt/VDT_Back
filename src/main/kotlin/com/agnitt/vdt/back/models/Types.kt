package com.agnitt.vdt.back.models

enum class Types {
    ALL,
    PAGE, PAGE_MODEL,
    /**page part/item types**/
    CHART, TABLE, SIDE_ITEM,
    /**chart types**/
    BIG, SMALL,
    /**side item types**/
    POPUP, DISCRETE_SLIDER, SWITCH_SLIDER, SWITCH, RADIO_GROUP, TEXT_VIEW
}