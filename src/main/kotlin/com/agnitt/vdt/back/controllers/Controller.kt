package com.agnitt.vdt.back.controllers

import com.agnitt.vdt.back.data.PageService
import com.agnitt.vdt.back.data.TemporaryStorage
import com.agnitt.vdt.back.data.fill
import com.agnitt.vdt.back.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    init {
        TemporaryStorage()
    }

    @Autowired
    lateinit var pageService: PageService

    @GetMapping("/fill_base")
    fun fillBase() = pageService.run {
        deleteAllPages()
        fill()
        getAllPageModels()
    }

    /** get **/

    @GetMapping("/get/page/all")
    fun getAllPages() = pageService.getAllPages()

    @GetMapping("/get/page/model/all")
    fun getAllPageModels() = pageService.getAllPageModels()

    @RequestMapping("/get/{type}/{id}")
    fun getItemById(@PathVariable id: Long, @PathVariable type: String): Any? = when (type) {
        Types.PAGE_MODEL.name -> pageService.getById<PageModel>(id)
        Types.PAGE.name -> pageService.getById<Page>(id)
        Types.CHART.name -> pageService.getById<Chart>(id)
        Types.TABLE.name -> pageService.getById<Table>(id)
        Types.SIDE_ITEM.name -> pageService.getById<SideItem>(id)
        else -> "[ERROR] Invalid type specified: $type\nCorrect types: ${Types.values().joinToString(", ") { it.name }}}"
    } ?: "[ERROR] Invalid id specified: $id"


    /** change **/

    @RequestMapping("/change/{id}/{currentValue}")
    fun changeItemById(@PathVariable id: Long, @PathVariable currentValue: Float) = pageService
            .getRelatedItemsAfterChange(id, currentValue) ?: "[ERROR] Invalid id specified: $id"

    @RequestMapping("/delete/page/{id}")
    fun deletePage(@PathVariable("id") id: Long) = pageService.deleteById<PageModel>(id)

    @RequestMapping("/delete/page/all")
    fun deleteAllPages() = pageService.deleteAllPages()

    /** tests **/

    @RequestMapping("/test/init")
    fun test(): Boolean {
        var idOwner = 0.toLong()
        var idOwner1 = 0.toLong()

        val page = PageModel(idOwner, "start", "table", mutableListOf(), mutableListOf())
        val page1 = PageModel(idOwner1, "start", "chart", mutableListOf(), mutableListOf())

        idOwner = pageService.insert(page)
        idOwner1 = pageService.insert(page1)

        val table = Table(0.toLong(), idOwner, mutableListOf(1f, 9f))
        val table1 = Table(0.toLong(), idOwner1, mutableListOf(21f, 29f))

        val chart = Chart(0.toLong(), idOwner, "hi", "lol", "main", mutableListOf(2f, 4f, 5f), mutableListOf(12f, 14f, 15f), 20f)
        val chart1 = Chart(0.toLong(), idOwner1, "hi", "lol", "main", mutableListOf(22f, 24f, 25f), mutableListOf(212f, 214f, 215f), 220f)

        val sideItem = SideItem(0.toLong(), idOwner, "switch_1", "switch", mutableListOf(0f, 1f), 4, 1f, mutableListOf(chart.id))
        val sideItem1 = SideItem(0.toLong(), idOwner1, "switch_1", "switch", mutableListOf(20f, 21f), 4, 21f, mutableListOf(chart.id))

        page.mainItemsIds = pageService.insert(table, chart)
        page.sideItemsIds = mutableListOf(pageService.insert(sideItem))

        page1.mainItemsIds = pageService.insert(table1, chart1)
        page1.sideItemsIds = mutableListOf(pageService.insert(sideItem1))

        pageService.update(page1)
        pageService.update(page)
        return true
    }

    @RequestMapping("/test/update/table/{id}")
    fun testUpdateTable(@PathVariable("id") id: Long): Table? {
        val table = pageService.getById<Table>(id)?.apply {
            dataList = mutableListOf(9999999.999999f)
        } ?: return null
        pageService.update(table)

        return pageService.getById<Table>(id)
    }

    @RequestMapping("/test/update/side_item/{id}/{currentValue}")
    fun testUpdateSideItem(@PathVariable("id") id: Long,
                           @PathVariable("currentValue") currentValue: Float): SideItem? {
        val item = pageService.getById<SideItem>(id)?.apply {
            this.currentValue = currentValue
        } ?: return null
        return pageService.update(item)
    }

//    @RequestMapping("/test/get/table/{id}")
//    fun testGetTable(@PathVariable("id") id: Long) = pageService.getById<Table>(id)


//            when (type) {
//        Types.PAGE_MODEL.value -> pageService.getById<PageModel>(id)
//        Types.PAGE.value -> pageService.getById<Page>(id)
//        Types.CHART.value -> pageService.getById<Chart>(id)
//        Types.TABLE.value -> pageService.getById<Table>(id)
//        Types.SIDE_ITEM.value -> pageService.getById<SideItem>(id)
//        else -> "[ERROR] Invalid type specified: $type\nCorrect types: ${Types.values().joinToString(", ") { it.value }}}"
//    } ?: "[ERROR] Invalid id specified: $id"

    /**
    @RequestMapping("/delete/file/database/{name}")
    fun deleteFileDatabase(@PathVariable("name") name: String): String {
    if (name.isEmpty()) return "Name db-file required"
    var path = Paths.get("$name")
    return if (!Files.isDirectory(path) && Files.exists(path)) {
    Files.delete(path)
    "Deleted $name"
    } else "[\$path] Could not delete $name: " +
    "${if (Files.isDirectory(path)) "it's dir "
    else if (!Files.exists(path)) "don't exist"
    else "hz"}"
    }
     **/
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

//    @RequestMapping("/change/{itemId}")
//    fun changeSideItemState(@PathVariable("itemId") itemId: Long,
//                         @RequestParam(name = "owner", defaultValue = "none") owner: Long,
//                         @RequestParam(name = "currentValue", defaultValue = "none") currentValue: Float)
//            : List<MainContentItem> {
//        return dao.getByOwner(owner).changeCurrentValue(currentValue).getconnectionItemsList()
//    }
}