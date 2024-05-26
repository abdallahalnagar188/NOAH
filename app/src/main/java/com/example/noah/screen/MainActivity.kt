package com.example.noah.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noah.screen.home.HomeContent
import com.example.noah.ui.theme.NOAHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOAHTheme {
                HomeContent()
            }
        }
    }
}