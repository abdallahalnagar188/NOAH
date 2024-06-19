package com.example.noah.screen.qrCood

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.json.JSONException
import org.json.JSONObject

class QrCodeViewModel : ViewModel() {
    var isFirebaseInitialized = mutableStateOf(false)
        private set

    fun handleQRCodeResult(context: Context, qrCodeResult: String) {
        try {
            val config = parseQRCodeResult(qrCodeResult)
            initializeFirebase(context, config)
        } catch (e: JSONException) {
            e.printStackTrace()
            // Handle JSON parsing error
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

    private fun initializeFirebase(context: Context, config: FirebaseConfig) {
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

data class FirebaseConfig(
    val apiKey: String,
    val authDomain: String,
    val databaseURL: String,
    val projectId: String,
    val storageBucket: String,
    val messagingSenderId: String,
    val appId: String
)
