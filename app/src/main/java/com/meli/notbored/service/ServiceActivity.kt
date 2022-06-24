package com.meli.notbored.service

import android.util.Log
import com.meli.notbored.domain.Activity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceActivity {
    private const val BASE_URL = "https://raw.githubusercontent.com/"
    private var serviceActivityAPI: ServiceActivityAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serviceActivityAPI = retrofit.create(ServiceActivityAPI::class.java)
    }

    fun getActivities(): MutableList<Activity>? {
        val call = serviceActivityAPI.getActitvities()
        val activities = call.execute().body()
        Log.d("SANTI-SERVICE", "$activities")
        return activities
    }
}