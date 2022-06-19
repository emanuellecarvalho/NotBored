package com.meli.notbored.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.ServiceActivities

class ModelActivityList: ViewModel(){

    private val  listOfActivityList: MutableLiveData<MutableList<Activity>> by lazy {
        MutableLiveData<MutableList<Activity>>().also {
            it.value = loadUserList()
        }
    }

    fun getList(): LiveData<MutableList<Activity>> {
        return this.listOfActivityList
    }

    private fun loadUserList(): MutableList<Activity>{
      return ServiceActivities.getList()
    }
}