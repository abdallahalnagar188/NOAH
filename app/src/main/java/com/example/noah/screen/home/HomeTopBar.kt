package com.example.noah.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noah.R
import com.example.noah.view_model.HomeViewModel

@Composable
fun HomeTopBar() {
    val vm: HomeViewModel = remember { HomeViewModel() }
    val lastFingerUser by vm.lastFingerUser.collectAsState()
    val doorFingerUsers by vm.doorFingerUsers.collectAsState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.background(Color.Transparent).padding(6.dp)
    ) {

        CardCounter(counter = lastFingerUser, name = "last finger",modifier = Modifier)
        Image(
            painter = painterResource(id = R.drawable.iconapp),
                contentDescription = "noah image top bar",
                modifier = Modifier
                    .size(130.dp)
                    .weight(0.3f)
            )
        CardCounter(counter = doorFingerUsers, name = "Users",modifier = Modifier)
        }

}