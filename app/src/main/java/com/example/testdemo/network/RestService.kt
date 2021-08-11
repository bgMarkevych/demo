package com.example.testdemo.network

import com.example.testdemo.model.IpResponse
import com.example.testdemo.model.LocationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RestService {

    @GET("/{ip}?access_key=25af7cf50ac74b09e64764370a49ce34")
    fun checkLocation(@Path("ip") ip: String): Single<LocationResponse>

    @GET
    fun getIp(@Url url: String = "https://api.ipify.org/?format=json"): Single<IpResponse>

}