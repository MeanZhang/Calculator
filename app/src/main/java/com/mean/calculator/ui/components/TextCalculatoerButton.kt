package com.mean.calculator.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mean.calculator.MainViewModel

@Composable
fun TextCalculatorButton(text: String, viewModel: MainViewModel?) {
    Surface(modifier = Modifier
        .height(48.dp)
        .width(72.dp)
        .clip(RoundedCornerShape(24.dp))
        .clickable { }) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text,
                fontWeight = FontWeight.W400,
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}