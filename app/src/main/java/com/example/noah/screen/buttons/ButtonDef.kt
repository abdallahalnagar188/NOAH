package com.example.noah.screen.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noah.R


@Composable
fun ButtonDef(icon: Int, namOfButton: String, onClick: (Boolean) -> Unit = {}) {
    val isSelectedState = remember { mutableStateOf(false) }
    val bg = if (isSelectedState.value) {
        colorResource(id = R.color.coffie)
    } else {
        colorResource(id = R.color.coffie2)
    }

    val tint = if (isSelectedState.value) {
        colorResource(id = R.color.coffie2)
    } else {
        colorResource(id = R.color.coffie)
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isSelectedState.value = !isSelectedState.value
                onClick(isSelectedState.value)
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.size(86.dp),
            colors = ButtonDefaults.buttonColors(
                bg,
                contentColor = Color.DarkGray // Icon color
            )
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                modifier = Modifier, tint = tint
            )
        }
        Text(
            text = namOfButton,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            style = TextStyle(color = Color.White),
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
