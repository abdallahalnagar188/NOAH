package com.example.noah.screen.qrCood


import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.json.JSONException
import org.json.JSONObject

data class FirebaseConfig(
    val apiKey: String,
    val authDomain: String,
    val databaseURL: String,
    val projectId: String,
    val storageBucket: String,
    val messagingSenderId: String,
    val appId: String
)

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
            authDomain = json.getString("authDomain"),
            databaseURL = json.getString("databaseURL"),
            projectId = json.getString("projectId"),
            storageBucket = json.getString("storageBucket"),
            messagingSenderId = json.getString("messagingSenderId"),
            appId = json.getString("appId")
        )
    }

    private fun saveConfig(context: Context, config: FirebaseConfig) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("firebase_config", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("apiKey", config.apiKey)
        editor.putString("authDomain", config.authDomain)
        editor.putString("databaseURL", config.databaseURL)
        editor.putString("projectId", config.projectId)
        editor.putString("storageBucket", config.storageBucket)
        editor.putString("messagingSenderId", config.messagingSenderId)
        editor.putString("appId", config.appId)
        editor.apply()
    }

    fun getConfig(context: Context): FirebaseConfig? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("firebase_config", Context.MODE_PRIVATE)
        val apiKey = sharedPreferences.getString("apiKey", null)
        val authDomain = sharedPreferences.getString("authDomain", null)
        val databaseURL = sharedPreferences.getString("databaseURL", null)
        val projectId = sharedPreferences.getString("projectId", null)
        val storageBucket = sharedPreferences.getString("storageBucket", null)
        val messagingSenderId = sharedPreferences.getString("messagingSenderId", null)
        val appId = sharedPreferences.getString("appId", null)

        return if (apiKey != null && authDomain != null && databaseURL != null && projectId != null && storageBucket != null && messagingSenderId != null && appId != null) {
            FirebaseConfig(apiKey, authDomain, databaseURL, projectId, storageBucket, messagingSenderId, appId)
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
                .setStorageBucket(config.storageBucket)
                .setGcmSenderId(config.messagingSenderId)
                .build()

            FirebaseApp.initializeApp(context, options)
            isFirebaseInitialized.value = true
        } catch (e: Exception) {
            e.printStackTrace()
            isFirebaseInitialized.value = false
        }
    }
}


