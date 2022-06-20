package com.meli.notbored.domain

import com.meli.notbored.R
import java.text.DecimalFormat
import java.util.*

data class Activity(var activity: String, var price: Float)

object ServiceActivities {
    fun getList(arrayList: Array<String>):MutableList<Activity> {
        val list = mutableListOf<Activity>()

        for (activity in arrayList) {
            list.add(Activity(activity, getRandomPrice()))
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
            list.add(Activity(activity, getRandomPrice()))
        }

        return list
    }

    fun getRandomPrice():Float{
        val random = 0.0 + Math.random() * (1.0 - 0.0)
        val s = DecimalFormat("0.0")
        return s.format(random).toFloat()
    }
}
