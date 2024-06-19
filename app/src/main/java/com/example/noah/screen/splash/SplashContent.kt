package com.example.noah.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.screen.buttons.CircularProgressAnimated
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("qrCode") {
            popUpTo("splash") { inclusive = true }
        }
    }
    SplashContent()
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bgcolor),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.iconapp),
                contentDescription = "Splash Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp)) // فاصل بين الصورة الأولى والثانية
            Image(
                painter = painterResource(id = R.drawable.noahimage),
                contentDescription = "Noah Image",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp)) // فاصل بين الصورة ومؤشر الدوران
            CircularProgressAnimated(modifier = Modifier.size(50.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSplash() {
    SplashContent()
}
