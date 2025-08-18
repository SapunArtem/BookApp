package com.example.bookapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bookapp.presentation.components.settings.language.LocalizationManager
import com.example.bookapp.presentation.screens.App
import com.example.bookapp.presentation.ui.theme.BookAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }

            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalizationManager.wrapContext(newBase))
    }
}


