package com.example.borja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.borja.ui.LiceoAccountApp
import com.example.borja.ui.theme.BorjaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BorjaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LiceoAccountApp()
                }
            }
        }
    }
}