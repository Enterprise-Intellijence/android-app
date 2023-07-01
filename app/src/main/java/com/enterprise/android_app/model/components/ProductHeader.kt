package com.enterprise.android_app.model.components

import androidx.annotation.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductHeader(name: String, condition: String, price: Double) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text( text = name, fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Text( text = condition, fontWeight = FontWeight.Light, fontSize = 16.sp)
        Text( text = price.toString(), fontWeight = FontWeight.Normal, fontSize = 16.sp)
    }
}
