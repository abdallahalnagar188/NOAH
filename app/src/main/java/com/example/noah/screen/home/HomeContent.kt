package com.example.noah.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeContent() {
    Box (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardItem(name = "Wifi Order")
            CardItem(name = "Add Finger Print")
            CardItem(name = "Delete Finger User ")
            CardItem(name = "Finger Mode")
            CardItem(name = "Un Lock")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                CardSmallItem(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(80.dp)
                        .padding(horizontal = 6.dp),
                    name = "Last User"
                )
                CardSmallItem(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(80.dp)
                        .padding(horizontal = 6.dp),
                    name = "Door Finger User"
                )
            }
        }
    }
}