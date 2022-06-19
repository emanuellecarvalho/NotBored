package com.meli.notbored.domain

import com.meli.notbored.R
import java.util.*

data class Activity(var activity: String)

object ServiceActivities {
    fun getList(arrayList: Array<String>):MutableList<Activity> {
        val list = mutableListOf<Activity>()

        for (activity in arrayList) {
            list.add(Activity(activity))
        }
        return list
    }

    fun getList(): MutableList<Activity>{
        val list = mutableListOf<Activity>()

        val arrayList = arrayListOf(
            "Educatinal",
            "Recreational",
            "Social",
            "Diy",
            "Charity",
            "Cooking",
            "Relaxation",
            "Music",
            "Busywork"
        )

        for (activity in arrayList){
            list.add(Activity(activity))
        }

        return list
    }
}
