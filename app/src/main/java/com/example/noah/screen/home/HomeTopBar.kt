package com.example.noah.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noah.R

@Composable
fun HomeTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
//            Text(
//                text = "NOAH APP",
//                color = Color.Black,
//                fontFamily = FontFamily.Default,
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Medium,
//                modifier = Modifier
//                    .padding(10.dp)
//                    .align(
//                        Alignment.CenterVertically
//                    )
//            )
        Card(
            modifier = Modifier.padding(12.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 7.dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.noah_topbar_imag),
                contentDescription = "noah image top bar",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
            )
        }

    }
}