package com.example.noah.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
fun SplashScreen(navController: NavController, onSplashComplete: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(2000)  // Delay for 2 seconds
        onSplashComplete()

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
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.noahimage),
                contentDescription = "Noah Image",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(40.dp))
            CircularProgressAnimated(modifier = Modifier.size(50.dp))

        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PreviewSplash() {
    SplashContent()
}