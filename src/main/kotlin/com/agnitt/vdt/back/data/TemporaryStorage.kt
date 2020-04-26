package com.agnitt.vdt.back.data

class TemporaryStorage {
    init {
        temp = this
    }

    companion object {
        lateinit var temp: TemporaryStorage
    }

    var mapOfChanges: HashMap<Long, Float> = hashMapOf()

    fun add(id: Long, value: Float) = mapOfChanges.put(id, value)
    fun update(id: Long, value: Float) = mapOfChanges.replace(id, value) ?: mapOfChanges.put(id, value)
    fun get(id: Long, defValue: Float) = mapOfChanges[id] ?: mapOfChanges.put(id, defValue) ?: defValue
    fun getAndUpdate(id: Long, newValue: Float, defValue: Float) = mapOfChanges.put(id, newValue) ?: defValue
}