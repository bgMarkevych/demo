package com.example.testdemo

import com.example.testdemo.model.LocationResponse
import com.example.testdemo.network.RestService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainModel(private val restService: RestService) {

    fun checkLocation(): Single<LocationResponse> =
        restService.getIp()
            .flatMap { restService.checkLocation(it.ip) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}