package com.example.noah.screen.qrCood

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import org.json.JSONException
import org.json.JSONObject

@Composable
fun QrScreen(navController: NavController) {
    val context = LocalContext.current as Activity
    val sharedPreferences = context.getSharedPreferences("qr_data", Context.MODE_PRIVATE)

    val launcher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            // Save the scanned data in SharedPreferences
            sharedPreferences.edit().putString("qr_data", result.contents).apply()

            // Attempt to parse the scanned data as JSON
            try {
                val jsonData = JSONObject(result.contents)

                // If parsing is successful, proceed with Firebase initialization
                val options = FirebaseOptions.Builder()
                    .setApiKey(jsonData.getString("apiKey"))
                    .setApplicationId(jsonData.getString("appId"))
                    .setDatabaseUrl(jsonData.getString("databaseURL"))
                    .setProjectId(jsonData.getString("projectId"))
                    .setStorageBucket(jsonData.getString("storageBucket"))
                    .setGcmSenderId(jsonData.getString("messagingSenderId"))
                    .build()

                if (FirebaseApp.getApps(context).isEmpty()) {
                    FirebaseApp.initializeApp(context, options)
                }
                // Navigate to Password Screen after Firebase initialization
                navController.navigate("password"){
                    popUpTo("qrCode"){
                        inclusive = true
                    }
                }
            } catch (e: JSONException) {
                // If parsing as JSON fails, handle non-JSON data
                Log.e("QrScreen", "Error parsing QR code data as JSON: ${e.message}")
                handleNonJsonData(result.contents)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            launcher.launch(ScanOptions())
        }) {
            Text("Scan QR Code")
        }
    }
}

private fun handleNonJsonData(data: String) {
    // Handle the case where the data is not JSON, e.g., it's a URL
    // For demonstration, just log it
    Log.d("QrScreen", "Non-JSON QR code data: $data")
    // Optionally show an error or information to the user
}
