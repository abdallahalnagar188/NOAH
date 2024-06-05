package com.example.noah.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noah.R

@Composable
fun CardCounter(counter: String, name: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .size(140.dp)
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier.size(90.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.color2_app)
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = counter,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(color = Color.White)
                )

            }
        }
        Text(
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            style = TextStyle(color = Color.White),
            modifier = Modifier.padding()
        )
    }

}