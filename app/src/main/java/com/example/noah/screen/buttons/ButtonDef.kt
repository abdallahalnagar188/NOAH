package com.example.noah.screen.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noah.R


@Composable
fun ButtonDef(icon: Int) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50),
        modifier = Modifier.size(86.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.coffie), // Background color of the button
            contentColor = Color.DarkGray // Icon color
        )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            modifier = Modifier,
        )

    }
}