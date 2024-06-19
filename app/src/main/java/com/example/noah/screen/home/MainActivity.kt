package com.example.noah.screen.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.noah.screen.qrCood.QrCodeViewModel

class MainActivity : ComponentActivity() {
    private val qrCodeViewModel: QrCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(qrCodeViewModel)
        }
    }
}
