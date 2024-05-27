package com.example.noah.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noah.R

@Composable
fun CardItem(name: String, onClick: () -> Unit = {}) {
    var switchState by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var pendingSwitchState by remember { mutableStateOf(false) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmation") },
            text = { Text("Are you sure you want to change the  state?") },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.color2_app)),
                    onClick = {
                        onClick()
                        switchState = pendingSwitchState
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    },colors = ButtonDefaults.buttonColors(colorResource(id = R.color.color2_app)),
                ) {
                    Text("Cancel")
                }
            }
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.color2_app)
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = name,
                style = TextStyle(color = Color.White),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Switch(
                checked = switchState,
                onCheckedChange = {
                    pendingSwitchState = it
                    showDialog = true
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }
    }
}