package com.example.noah.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .background(Color.Transparent)
                .padding(6.dp)
        ) {
            IconButton(
                onClick = { navController.navigate("aboutUs")},
                modifier = Modifier
                    .size(70.dp)
                    .padding(horizontal = 12.dp).weight(0.2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_about),
                    contentDescription = "icon setting",
                    modifier = Modifier.size(50.dp),
                    tint = colorResource(
                        id = R.color.coffie
                    )
                )

            }

            Image(
                painter = painterResource(id = R.drawable.noahimage),
                contentDescription = "noah image top bar",
                modifier = Modifier.align(Alignment.CenterVertically).weight(0.6f)

            )

            IconButton(
                onClick = { navController.navigate("settings")},
                modifier = Modifier
                    .size(60.dp)
                    .padding(horizontal = 12.dp).weight(0.2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "icon setting",
                    modifier = Modifier.size(40.dp),
                    tint = colorResource(
                        id = R.color.coffie
                    )
                )
            }
        }
    }
}