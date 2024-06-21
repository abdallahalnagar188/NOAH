package com.example.noah.screen.qrCood

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.models.FirebaseConfig
import com.google.gson.Gson
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QrScreen(navController: NavController) {
    val context = LocalContext.current as Activity

    // Check if FirebaseConfig is already stored
    val storedConfig = SharedPreferencesHelper.getFirebaseConfig(context)

    LaunchedEffect(Unit) {
        if (storedConfig != null) {
            initializeFirebaseApp(context, storedConfig)
            navController.navigate("password") {
                popUpTo("qrCode") {
                    inclusive = true
                }
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            // Attempt to parse the scanned data as JSON
            try {
                val jsonData = Gson().fromJson(result.contents, FirebaseConfig::class.java)

                // Save the scanned data in SharedPreferences
                SharedPreferencesHelper.saveFirebaseConfig(context, jsonData)

                // Initialize Firebase with the scanned data
                initializeFirebaseApp(context, jsonData)

                // Navigate to Password Screen after Firebase initialization
                navController.navigate("password") {
                    popUpTo("qrCode") {
                        inclusive = true
                    }
                }
            } catch (e: Exception) {
                // If parsing as JSON fails, handle non-JSON data
                Log.e("QrScreen", "Error parsing QR code data as JSON: ${e.message}")
                handleNonJsonData(result.contents)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bgcolor),
                    contentScale = ContentScale.FillBounds
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription = "qr image",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            Button(
                onClick = {
                    launcher.launch(ScanOptions())
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.coffie)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    "Scan QR Code",
                    color = colorResource(id = R.color.coffie2),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )
            }
        }

    }
}

private fun handleNonJsonData(data: String) {
    // Handle the case where the data is not JSON, e.g., it's a URL
    // For demonstration, just log it
    Log.d("QrScreen", "Non-JSON QR code data: $data")
    // Optionally show an error or information to the user
}
