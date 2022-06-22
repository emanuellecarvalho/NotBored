package com.meli.notbored.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.ServiceActivities

class ModelActivityList: ViewModel(){

    private val activitySelected = MutableLiveData<Activity>()

    fun setActivitySelected(activity: Activity){
        Log.d("SANTI", "setActivitySelected ${activity}")
        activitySelected.value = activity
    }

    fun selectedActivity(): LiveData<Activity>{
        Log.d("SANTI", "selectedActivity ${activitySelected.value?.activity}")
        return activitySelected
    }

    private val participantNumber = MutableLiveData<Int>()
    fun participantNumber(number: Int){
        participantNumber.value = number
    }

    fun getParticipantNumber(): LiveData<Int>{
        return this.participantNumber
    }

    private val  listOfActivityList: MutableLiveData<MutableList<Activity>> by lazy {
        MutableLiveData<MutableList<Activity>>().also {
            it.value = loadUserList()
        }
    }

    fun getList(): LiveData<MutableList<Activity>> {
        return listOfActivityList
    }

    private fun loadUserList(): MutableList<Activity>{
      return ServiceActivities.getList()
    }
}