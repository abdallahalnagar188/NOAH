package com.example.noah.screen.qrCood


import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.noah.models.FirebaseConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject





object SharedPreferencesHelper {
    private const val PREF_NAME = "qr_data"
    private const val CONFIG_KEY = "firebase_config"

    fun saveFirebaseConfig(context: Context, config: FirebaseConfig) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonString = gson.toJson(config)
        editor.putString(CONFIG_KEY, jsonString)
        editor.apply()
    }

    fun getFirebaseConfig(context: Context): FirebaseConfig? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString(CONFIG_KEY, null)
        return if (jsonString != null) {
            val gson = Gson()
            gson.fromJson(jsonString, FirebaseConfig::class.java)
        } else {
            null
        }
    }
}

fun initializeFirebaseApp(context: Context, config: FirebaseConfig) {
    val options = FirebaseOptions.Builder()
        .setApiKey(config.apiKey)
        .setApplicationId(config.appId)
        .setDatabaseUrl(config.databaseURL)
        .setProjectId(config.projectId)
        .build()

    if (FirebaseApp.getApps(context).isEmpty()) {
        FirebaseApp.initializeApp(context, options)
    }
}

class QrCodeViewModel : ViewModel() {
    var isFirebaseInitialized = mutableStateOf(false)
        private set

    fun handleQRCodeResult(context: Context, qrCodeResult: String) {
        try {
            val config = parseQRCodeResult(qrCodeResult)
            saveConfig(context, config)
            initializeFirebase(context, config)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun parseQRCodeResult(qrCodeResult: String): FirebaseConfig {
        val json = JSONObject(qrCodeResult)
        return FirebaseConfig(
            apiKey = json.getString("apiKey"),
            databaseURL = json.getString("databaseURL"),
            projectId = json.getString("projectId"),
            appId = json.getString("appId")
        )
    }

    private fun saveConfig(context: Context, config: FirebaseConfig) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("firebase_config", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("apiKey", config.apiKey)
        editor.putString("databaseURL", config.databaseURL)
        editor.putString("projectId", config.projectId)
        editor.putString("appId", config.appId)
        editor.apply()
    }

    fun getConfig(context: Context): FirebaseConfig? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("firebase_config", Context.MODE_PRIVATE)
        val apiKey = sharedPreferences.getString("apiKey", null)
        val authDomain = sharedPreferences.getString("authDomain", null)
        val databaseURL = sharedPreferences.getString("databaseURL", null)
        val projectId = sharedPreferences.getString("projectId", null)
        val appId = sharedPreferences.getString("appId", null)

        return if (apiKey != null && authDomain != null && databaseURL != null && projectId != null  && appId != null) {
            FirebaseConfig(apiKey, databaseURL, projectId, appId)
        } else {
            null
        }
    }

    fun initializeFirebase(context: Context, config: FirebaseConfig?) {
        if (config == null) {
            isFirebaseInitialized.value = false
            return
        }

        try {
            val options = FirebaseOptions.Builder()
                .setApiKey(config.apiKey)
                .setApplicationId(config.appId)
                .setDatabaseUrl(config.databaseURL)
                .setProjectId(config.projectId)
                .build()

            FirebaseApp.initializeApp(context, options)
            isFirebaseInitialized.value = true
        } catch (e: Exception) {
            e.printStackTrace()
            isFirebaseInitialized.value = false
        }
    }
}


