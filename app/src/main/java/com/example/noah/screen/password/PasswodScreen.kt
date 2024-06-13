package com.example.noah.screen.password

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.view_model.PasswordManager

@Composable
fun PasswordScreen(navController: NavController) {
    val context = LocalContext.current
    val passwordManager = remember { PasswordManager(context) }
    var password by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }
    var isFirstTime by remember { mutableStateOf(!passwordManager.isPasswordSet()) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bgcolor),
                contentScale = ContentScale.FillBounds
            )
    ) {
        if (isFirstTime) {
            Text(
                "Set your password",
                style = TextStyle(color = Color.White),
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif
            )
        } else {
            Text(
                "Enter your password",
                style = TextStyle(color = Color.White),
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", style = TextStyle(color = Color.White)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            shape = RoundedCornerShape(25.dp), colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.coffie))
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isFirstTime) {
            OutlinedTextField(
                value = confirmedPassword,
                onValueChange = { confirmedPassword = it },
                label = { Text("Confirm Password", style = TextStyle(color = Color.White)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = (colorResource(id = R.color.coffie))
                )
            )

            Spacer(modifier = Modifier.height(60.dp))
        }

        Button(modifier = Modifier
            .border(
                2.5.dp,
                colorResource(id = R.color.coffie2),
                shape = RoundedCornerShape(20.dp)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.coffie)
            ),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                if (isFirstTime) {
                    if (password == confirmedPassword && password.length == 6) {
                        passwordManager.savePassword(password)
                        isFirstTime = false
                        navController.navigate("home") {
                            popUpTo("password") { inclusive = true }
                        }
                    } else {
                        errorMessage = "please enter a 6 digit password"
                    }
                } else {
                    val storedPassword = passwordManager.getPassword()
                    if (storedPassword == password) {
                        navController.navigate("home") {
                            popUpTo("password") { inclusive = true }
                        }
                    } else {
                        errorMessage = "Incorrect password"
                    }
                }
            }) {
            Text(
                text = "Login",
                style = TextStyle(color = Color.Black),
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
            )
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                errorMessage,
                color = Color.Red,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

