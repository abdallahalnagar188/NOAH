package com.example.noah.view_model

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Repo (activity: Activity){
    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences("NoahDoor",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()


    fun addFingerToShard(addFingerPrint:Boolean){
        editor.putBoolean("addFinger",addFingerPrint)
        editor.apply()
    }
    fun deleteUsersToShard(deleteUsers:Boolean){
        editor.putBoolean("deleteUsers",deleteUsers)
        editor.apply()
    }

    fun unlockToShard(unlock:Boolean){
        editor.putBoolean("unlock",unlock)
        editor.apply()
    }

    fun fingerModeToShard(fingerMode:Boolean){
        editor.putBoolean("fingerMode",fingerMode)
        editor.apply()
    }
    fun wifiOrderToShard(wifiOrder:Boolean){
        editor.putBoolean("wifiOrder",wifiOrder)
        editor.apply()
    }

    fun get (key:String):Boolean{
        return  sharedPreferences.getBoolean(key,false)
    }
}