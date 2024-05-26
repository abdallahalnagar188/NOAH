package com.example.noah.screen.aboutUs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

var screen_route = "aboutUsScreen"
@Composable
fun AboutUsScree(navController: NavController) {
    AboutUsContent(navController = navController)
}

@Composable
fun AboutUsContent(navController: NavController) {
    Box (modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "About Us Screen")
        }
    }
}
