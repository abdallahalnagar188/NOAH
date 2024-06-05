package com.example.noah.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noah.screen.aboutUs.AboutUsScree
import com.example.noah.screen.setting.SettingScreen


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeContent(navController) }
        composable("aboutUs") { AboutUsScree(navController) }
        composable("settings") { SettingScreen(navController) }
    }
}