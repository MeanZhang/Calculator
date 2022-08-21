package com.mean.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mean.calculator.ButtonParams
import com.mean.calculator.ButtonType
import com.mean.calculator.MainViewModel


@Composable
fun ColumnScope.LandscapeOperationArea(viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .weight(2F)
            .padding(top = 16.dp)
            .padding(start = 24.dp)
    )
    {
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for (row in textButtons) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (button in row) {
                        TextCalculatorButton(button, isPortrait = false, viewModel = viewModel)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(5f)
                .fillMaxHeight()
                .padding(end = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for (row in buttons) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (button in row) {
                        CalculatorButton(button, isPortrait = false, viewModel = viewModel)
                    }
                }
            }
        }
    }
    Spacer(
        modifier = Modifier
            .windowInsetsBottomHeight(WindowInsets.navigationBars)
            .fillMaxWidth()
            .background(Color.Transparent)
    )
}

private val buttons = listOf(
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "7"),
        ButtonParams(ButtonType.NUMBER, text = "8"),
        ButtonParams(ButtonType.NUMBER, text = "9"),
        ButtonParams(ButtonType.OPERRATION, text = "÷"),
        ButtonParams(ButtonType.AC, text = "AC"),
    ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "4"),
        ButtonParams(ButtonType.NUMBER, text = "5"),
        ButtonParams(ButtonType.NUMBER, text = "6"),
        ButtonParams(ButtonType.OPERRATION, text = "×"),
        ButtonParams(ButtonType.BRACKETS, text = "( )"),

        ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "1"),
        ButtonParams(ButtonType.NUMBER, text = "2"),
        ButtonParams(ButtonType.NUMBER, text = "3"),
        ButtonParams(ButtonType.OPERRATION, text = "-"),
        ButtonParams(ButtonType.OPERRATION, text = "%"),
    ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "0"),
        ButtonParams(ButtonType.DOT, text = "•"),
        ButtonParams(ButtonType.DELETE),
        ButtonParams(ButtonType.OPERRATION, text = "+"),
        ButtonParams(ButtonType.EQUAL, text = "=")
    )
)

private val textButtons = listOf(
    listOf(
        "DEG", "√", "π",
    ),
    listOf(
        "INV", "^", "!",
    ),
    listOf(
        "sin", "cos", "tan"
    ),
    listOf(
        "e", "ln", "log"
    )
)