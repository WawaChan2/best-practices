package com.wawa.bestpractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wawa.bestpractices.ui.BestPracticesApp
import com.wawa.bestpractices.ui.theme.BestPracticesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BestPracticesTheme {
        BestPracticesApp()
      }
    }
  }
}