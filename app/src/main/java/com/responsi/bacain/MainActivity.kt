package com.responsi.bacain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.responsi.bacain.ui.navigation.AnimeNavGraph
import com.responsi.bacain.ui.theme.BacaInTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BacaInTheme {
                AnimeNavGraph()
            }
        }
    }
}