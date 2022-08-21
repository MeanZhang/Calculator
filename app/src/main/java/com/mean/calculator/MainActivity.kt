package com.mean.calculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.mean.calculator.ui.components.DisplayArea
import com.mean.calculator.ui.components.LandscapeOperationArea
import com.mean.calculator.ui.components.PortraitOperationArea
import com.mean.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val viewModel: MainViewModel by viewModels()
        setContent {
            CalculatorTheme {
                Content(viewModel)
            }
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel) {
    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DisplayArea(viewModel, isPortrait)
        if (isPortrait) {
            PortraitOperationArea(viewModel)
        }else{
            LandscapeOperationArea(viewModel)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    CalculatorTheme {
        Content(MainViewModel())
    }
}