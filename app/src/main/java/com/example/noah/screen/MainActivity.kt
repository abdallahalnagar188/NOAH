package com.example.noah.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noah.screen.aboutUs.AboutUsContent
import com.example.noah.screen.aboutUs.AboutUsScree
import com.example.noah.screen.home.HomeContent
import com.example.noah.screen.home.MyApp
import com.example.noah.ui.theme.NOAHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOAHTheme {
                MyApp()
            }
        }
    }
}