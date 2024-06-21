package com.example.noah.screen.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noah.screen.qrCood.SharedPreferencesHelper
import com.example.noah.screen.qrCood.initializeFirebaseApp
import com.example.noah.ui.theme.NOAHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shared = getSharedPreferences("firstLaunch", MODE_PRIVATE)
        val isFirst = shared.getBoolean("firstLaunch", true)
        if (isFirst) {
            shared.edit().putBoolean("firstLaunch", false).apply()
        }
        val startDestination = if (isFirst) {
            "qrCode"
        }else{
            "password"
        }

        // Retrieve FirebaseConfig from shared preferences
        val firebaseConfig = SharedPreferencesHelper.getFirebaseConfig(this)

        // Initialize Firebase if config exists
        firebaseConfig?.let {
            initializeFirebaseApp(this, it)
        }
        setContent {
            NOAHTheme {
                MyApp(startDestination)
            }
        }
    }
}
