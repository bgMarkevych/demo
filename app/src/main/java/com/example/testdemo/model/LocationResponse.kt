package com.example.testdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocationResponse(
    @Expose
    @SerializedName("country_code")
    var countryCode: String,
    @Expose
    var ip: String
)