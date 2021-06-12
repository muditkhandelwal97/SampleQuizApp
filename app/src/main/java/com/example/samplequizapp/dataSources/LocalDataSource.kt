package com.example.samplequizapp.dataSources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalDataSource<K>: DataSourceInterface<K> {

    override fun fetchData(path: String): K? {
        val type = object : TypeToken<K>() {}.type
        return Gson().fromJson(path, type)
    }

}