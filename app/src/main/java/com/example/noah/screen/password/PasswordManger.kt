package com.example.noah.screen.password

import android.content.Context
import android.content.SharedPreferences

class PasswordManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    fun savePassword(password: String) {
        sharedPreferences.edit().putString("password", password).apply()
    }

    fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    fun isPasswordSet(): Boolean {
        return getPassword() != null
    }
}
