package com.example.noah.screen.qrCood

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.zxing.integration.android.IntentIntegrator

@Composable
fun QrCodeScreen(navController: NavController, qrCodeViewModel: QrCodeViewModel) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val integrator = IntentIntegrator(context as Activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan a QR code")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()

    }
}


