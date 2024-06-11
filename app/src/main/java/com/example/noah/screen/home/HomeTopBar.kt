package com.example.noah.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noah.R

@Composable
fun HomeTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // زر التنقل إلى شاشة "aboutUs"
            IconButton(
                onClick = { navController.navigate("aboutUs") },
                modifier = Modifier
                    .size(70.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_about),
                    contentDescription = "Icon About Us",
                    modifier = Modifier.size(50.dp),
                    tint = colorResource(id = R.color.coffie)
                )
            }

            // صورة الشعار في الوسط
            Image(
                painter = painterResource(id = R.drawable.noahimage),
                contentDescription = "Noah Image Top Bar",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            // زر التنقل إلى شاشة "settings"
            IconButton(
                onClick = { navController.navigate("settings") },
                modifier = Modifier
                    .size(60.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Icon Settings",
                    modifier = Modifier.size(40.dp),
                    tint = colorResource(id = R.color.coffie)
                )
            }
        }
    }
}
