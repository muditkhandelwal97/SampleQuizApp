package com.example.samplequizapp.dataSources

interface DataSourceInterface<K> {
    fun fetchData(path: String): K?
}