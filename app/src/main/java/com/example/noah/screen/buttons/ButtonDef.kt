package com.example.noah.screen.buttons

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun ButtonDef(
    icon: Int,
    namOfButton: String,
    onClick: (Boolean) -> Unit = {},
    boolean: Boolean = false,
    isLoading: Boolean,
    errorMessage: String?
) {
    val isSelectedState = rememberSaveable { mutableStateOf(boolean) }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            // زر التشغيل
            Button(
                onClick = {
                    isSelectedState.value = !isSelectedState.value
                    onClick(isSelectedState.value)
                },
                modifier = Modifier
                    .size(86.dp)
                    .border(
                        2.5.dp,
                        color = colorResource(id = R.color.coffie2),
                        CircleShape
                    )
                    .shadow(elevation = 12.dp, shape = CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.coffie),
                    contentColor = Color.DarkGray // Icon color
                )
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon",
                    modifier = Modifier,
                    tint = colorResource(id = R.color.coffie2)
                )
            }


            if (isLoading) {
                CircularProgressAnimated(
                    modifier = Modifier.size(88.dp)
                )
            }
        }

        // اسم الزر
        Text(
            text = namOfButton,
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            style = TextStyle(color = Color.White)
        )

        // رسالة الخطأ إن وجدت
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Composable
 fun CircularProgressAnimated(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(animation = tween(900)),
        label = ""
    )
    CircularProgressIndicator(
        progress = progressAnimationValue,
        color = colorResource(id = R.color.coffie),
        modifier = modifier
    )
}

