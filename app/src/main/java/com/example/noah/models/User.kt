package com.example.noah.models

import com.google.gson.annotations.SerializedName

data class User(

    val addFingerPrint: Boolean,
    val deleteFingerUsers: Boolean,
    val doorFingerUsers: String,
    val fingerMode: Boolean,
    @SerializedName("LastFingerUser")
    val lastFingerUser: String,
    val unlock: Boolean,
    val wifiOrder: Boolean,
)
