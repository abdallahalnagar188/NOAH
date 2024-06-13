package com.example.noah.screen.qrCood

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noah.R

@Composable
fun QrScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bgcolor),
                    contentScale = ContentScale.FillBounds
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription = "Qr Image", Modifier.padding(top = 80.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {
                    navController.navigate("password") {
                        popUpTo("qrCode") { inclusive = true }
                    }
                },
                Modifier.border(
                    2.5.dp,
                    colorResource(id = R.color.coffie2),
                    shape = RoundedCornerShape(20.dp)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.coffie)
                ), shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Scan Qr",
                    style = TextStyle(color = Color.Black),
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.W600 ,
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQr(modifier: Modifier = Modifier) {

}