package com.meli.notbored.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.notbored.domain.Activity
import com.meli.notbored.service.ServiceActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelActivityList: ViewModel(){

    private val activitySelected = MutableLiveData<Activity>()

    fun setActivitySelected(activity: Activity){
        activitySelected.value = activity
    }

    fun selectedActivity(): LiveData<Activity>{
        return activitySelected
    }

    private val participantNumber = MutableLiveData<Int>()
    fun participantNumber(number: Int){
        participantNumber.value = number
    }

    fun getParticipantNumber(): LiveData<Int>{
        return this.participantNumber
    }

    //list
    private val listOfActivityList = MutableLiveData<MutableList<Activity>>()

    fun getList(): LiveData<MutableList<Activity>> {
        Log.d("SANTI-MODEL", "${listOfActivityList.value}")
        return listOfActivityList
    }

    fun loadUserList(){
        viewModelScope.launch(Dispatchers.IO) {
            getActivitiesFromServer()?.let {
                listOfActivityList.postValue(it)
                Log.d("SANTI-cou", "$it")
            }
        }
    }

    private suspend fun getActivitiesFromServer(): MutableList<Activity>? {
        return ServiceActivity.getActivities()
    }
}