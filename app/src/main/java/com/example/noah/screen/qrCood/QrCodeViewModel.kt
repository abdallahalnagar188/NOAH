package com.example.noah.screen.qrCood


import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class QrCodeViewModel : ViewModel() {
    private val _isFirebaseInitialized = mutableStateOf(false)
    val isFirebaseInitialized: Boolean get() = _isFirebaseInitialized.value

    fun onQrCodeScanned(contents: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                initializeFirebase(context, contents)
                _isFirebaseInitialized.value = true
            } catch (e: JSONException) {
                e.printStackTrace()
                // Handle JSON parsing error
            }
        }
    }

    private fun initializeFirebase(context: Context, qrData: String) {
        try {
            val jsonObject = JSONObject(qrData)
            val options = FirebaseOptions.Builder()
                .setApplicationId(jsonObject.getString("appId"))
                .setApiKey(jsonObject.getString("apiKey"))
                .setDatabaseUrl(jsonObject.getString("databaseURL"))
                .setStorageBucket(jsonObject.getString("storageBucket"))
                .setProjectId(jsonObject.getString("projectId"))
                .build()

            FirebaseApp.initializeApp(context, options, jsonObject.getString("projectId"))
        } catch (e: JSONException) {
            throw e  // Re-throw the exception to be caught in the outer try-catch block
        }
    }
}


