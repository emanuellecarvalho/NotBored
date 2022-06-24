package com.meli.notbored.service

import com.meli.notbored.domain.Activity
import retrofit2.Call
import retrofit2.http.GET

interface ServiceActivityAPI {
    @GET("SHE21/staticsfiles/main/activities.json")
    fun getActitvities(): Call<MutableList<Activity>>
}