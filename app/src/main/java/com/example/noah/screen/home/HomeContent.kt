package com.example.noah.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeContent() {
    Box (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardItem(name = "Add Finger Print")
            CardItem(name = "Delete Finger User ")
            CardItem(name = "Finger Mode")
            CardItem(name = "Un Lock")
            CardItem(name = "Wifi Order")

        }
    }
}