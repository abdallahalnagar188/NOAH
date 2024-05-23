package com.example.noah.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noah.screen.MainActivity
import com.example.noah.screen.splash.ui.theme.NOAHTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOAHTheme {
                SplashContent()
                Handler(Looper.getMainLooper()).postDelayed({
                    startHomeActivity()
                }, 1500)

            }
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}