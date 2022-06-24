package com.meli.notbored.service

class ActivityRepository constructor(private val retrofitService: ServiceActivity) {
    fun getAct() = retrofitService.getActivities()
}