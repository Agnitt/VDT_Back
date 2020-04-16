package com.agnitt.vdt.back.controllers

import com.agnitt.vdt.back.data.PageService
import com.agnitt.vdt.back.models.*
import com.agnitt.vdt.back.utils.uniqueId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    @Autowired
    lateinit var pageService: PageService

    @GetMapping("/get/pages")
    fun getAllPage() = pageService.getAllPages()

    @GetMapping("/get/page/{id}")
    fun getPage(@PathVariable("id") id: Long) = pageService.getPageModelById(id)

    @RequestMapping("/delete/page/{id}")
    fun deletePage(@PathVariable("id") id: Long) = pageService.deletePageModelById(id)

    @RequestMapping("/save/page")
    fun savePage(@RequestBody page: PageModel): Long = pageService.insert(page)


    @RequestMapping("/test")
    fun test(): Boolean {
        val idOwner = uniqueId()
        val idOwner1 = uniqueId()

        val table = Table(uniqueId(), idOwner, listOf(1f, 9f))
        val table1 = Table(uniqueId(), idOwner1, listOf(21f, 29f))

        val chart = Chart(uniqueId(), idOwner, "hi", "lol", "main", listOf(2f, 4f, 5f), listOf(12f, 14f, 15f), 20f)
        val chart1 = Chart(uniqueId(), idOwner1, "hi", "lol", "main", listOf(22f, 24f, 25f), listOf(212f, 214f, 215f), 220f)

        val sideItem = SideItem(uniqueId(), idOwner, "switch_1", "bool", "switch", listOf(0f, 1f), 1f, chart.id)
        val sideItem1 = SideItem(uniqueId(), idOwner1, "switch_1", "bool", "switch", listOf(20f, 21f), 21f, chart.id)

        val page = PageModel(idOwner, "start", "table", listOf(table.id, chart.id), listOf(sideItem.id))
        val page1 = PageModel(idOwner1, "start", "chart", listOf(chart1.id, table1.id), listOf(sideItem1.id))

        pageService.insert(table)
        pageService.insert(chart)
        pageService.insert(sideItem)
        pageService.insert(page)

        pageService.insert(table1)
        pageService.insert(chart1)
        pageService.insert(sideItem1)
        pageService.insert(page1)
        return true
    }
//
//    @RequestMapping("/page")
//    fun getPage() = Page(1, "sens", "table",
////            , listOf(2), listOf(3, 4))
//            listOf(Table(2, 1, listOf(10f, 100f))),
//            listOf(SideItem(1000, 1, "side", "sht", "seekbar",
//                    listOf(10f, 20f, 30f), 20f, 2)))
////
//    @RequestMapping("/string/{value}")
//    fun manipulateString(@PathVariable("value") value: String,
//                         @RequestParam(name = "operation", defaultValue = "none") operation: String) : String {
//        return when (operation.toUpperCase()) {
//            "REVERSE" -> value.reversed()
//            "UPPER" -> value.toUpperCase()
//            "LOWER" -> value.toLowerCase()
//            else -> value
//        }
//    }

//    @RequestMapping("/get/pages")
//    fun getPages() = dao.getAllPages()

//    @RequestMapping("/change/{itemId}")
//    fun changeSideItemState(@PathVariable("itemId") itemId: Long,
//                         @RequestParam(name = "owner", defaultValue = "none") owner: Long,
//                         @RequestParam(name = "currentValue", defaultValue = "none") currentValue: Float)
//            : List<MainContentItem> {
//        return dao.getByOwner(owner).changeCurrentValue(currentValue).getconnectionItemsList()
//    }
}