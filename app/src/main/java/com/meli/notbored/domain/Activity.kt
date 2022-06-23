package com.meli.notbored.domain

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random

enum class EXTRAS(key: String) {
    ATIVIDADE(key = "ATIVIDADE"),
    NUMBER_PARTICIPANT(key = "NUMBER_PARTICIPANT")
}

data class Activity(
    var activity: String?,
    var description: String?,
    var price: Float,
    var isRandomic: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(activity)
        parcel.writeString(description)
        parcel.writeFloat(price)
        parcel.writeByte(if (isRandomic) 1 else 0)
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

    fun getList(): MutableList<Activity> {
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

        val arrayListDesc = arrayListOf(
            "Educational is good",
            "Recreational, let's go there",
            "Social ... i don't know",
            "Diy! Oh no, i'm busy",
            "Charity. I'm poor",
            "Cooking. You cook for me",
            "Relaxation. I need it.",
            "Music. Every single day",
            "Busywork. My job already does that."
        )

        for ((count, activity) in arrayList.withIndex()) {
            list.add(Activity(activity, arrayListDesc[count], getRandomPrice()))
        }

        return list
    }

    fun getRandomActivity(activityList: MutableList<Activity>): Activity {
        val range = Random.nextInt(activityList.size - 0) + 0
        val activity = activityList[range]
        activity.isRandomic = true
        return activity
    }

    private fun getRandomPrice(): Float {
        val random: Double = 0.0f + Math.random() * (1.0f - 0.0f)
        return random.toFloat()
    }

    fun priceCategory(priceParameter: Float): String {
        if (priceParameter == 0f) return "Free"
        else if (priceParameter > 0 && priceParameter < 0.3f) return "Low"
        else if (priceParameter > 0.3f && priceParameter < 0.6f) return "Medium"
        else if (priceParameter > 0.6f) return "High"
        return "Price not found"
    }
}
