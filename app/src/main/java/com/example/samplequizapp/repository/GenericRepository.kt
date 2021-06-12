package com.example.samplequizapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplequizapp.dataSources.DataSourceInterface

class GenericRepository<K>(private val dataSource: DataSourceInterface<K>) {

    fun fetchData(path: String): MutableLiveData<K> {
        val data = MutableLiveData<K>()
        data.value = dataSource.fetchData(path)
        return data
    }
}