package com.example.noah.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noah.screen.aboutUs.AboutUsScreen
import com.example.noah.screen.password.PasswordScreen
import com.example.noah.screen.qrCood.QrScreen
import com.example.noah.screen.setting.SettingsScreen
import com.example.noah.screen.splash.SplashScreen

@Composable
fun MyApp(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) { navController.navigate(startDestination) } }
        composable("qrCode") {
            QrScreen(navController)
        }
        composable("password") { PasswordScreen(navController) }
        composable("home") { HomeContent(navController) }
        composable("aboutUs") { AboutUsScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }


}
