package com.example.nav3sampies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nav3sampies.ui.screens.detail.Navigation
import com.example.nav3sampies.ui.theme.Nav3SampiesTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nav3SampiesTheme {
                Navigation()
            }
        }
    }
}
