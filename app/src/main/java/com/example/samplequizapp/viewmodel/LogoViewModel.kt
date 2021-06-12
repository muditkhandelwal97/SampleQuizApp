package com.example.samplequizapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.samplequizapp.models.Logo
import com.example.samplequizapp.repository.GenericRepository

class LogoViewModel(application: Application, private val repository: GenericRepository<ArrayList<Logo>>): AndroidViewModel(application) {

    private var data: MutableLiveData<ArrayList<Logo>>? = null

    private fun fetchData(path: String) {
        data = repository.fetchData(path)
    }

    fun getData(path: String): MutableLiveData<ArrayList<Logo>>? {
        if (data == null) {
            fetchData(path)
        }
        return data
    }

}