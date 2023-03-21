package com.example.mosca.ui.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessage(message: String){
    Text(text = message, fontSize = 12.sp, color = Color.Red)
}