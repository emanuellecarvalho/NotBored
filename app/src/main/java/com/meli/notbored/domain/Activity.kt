package com.meli.notbored.domain

import android.os.Parcel
import android.os.Parcelable
import java.text.DecimalFormat
import kotlin.random.Random

enum class EXTRAS(key: String){
    ATIVIDADE(key = "ATIVIDADE"),
    NUMBER_PARTICIPANT(key = "NUMBER_PARTICIPANT")
}

data class Activity(var activity: String?, var price: Float):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(activity)
        parcel.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Activity> {
        override fun createFromParcel(parcel: Parcel): Activity {
            return Activity(parcel)
        }

        override fun newArray(size: Int): Array<Activity?> {
            return arrayOfNulls(size)
        }
    }
}

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
            "Educational",
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

    fun getRandomActivity(activityList: MutableList<Activity>):Activity{
        val range = Random.nextInt(activityList.size - 0) + 0
        return activityList[range]
    }

    private fun getRandomPrice(): Float {
        val random:Double = 0.0f + Math.random() * (1.0f - 0.0f)
        //val formatDecimal:DecimalFormat = DecimalFormat("0.0")
        return random.toFloat()
    }

}
