package com.mean.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mean.calculator.ButtonParams
import com.mean.calculator.ButtonType
import com.mean.calculator.MainViewModel


@Composable
fun ColumnScope.PortraitOperationArea(viewModel: MainViewModel) {
    val expanded = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .weight(2F)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ExpandedButtons(expanded, viewModel)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            for (row in buttons) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (button in row) {
                        CalculatorButton(
                            button = button,
                            expanded = expanded.value,
                            isPortrait = true,
                            viewModel = viewModel
                        )
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
        ButtonParams(ButtonType.AC, text = "AC"),
        ButtonParams(ButtonType.BRACKETS, text = "( )"),
        ButtonParams(ButtonType.OPERRATION, text = "%"),
        ButtonParams(ButtonType.OPERRATION, text = "÷")
    ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "7"),
        ButtonParams(ButtonType.NUMBER, text = "8"),
        ButtonParams(ButtonType.NUMBER, text = "9"),
        ButtonParams(ButtonType.OPERRATION, text = "×")
    ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "4"),
        ButtonParams(ButtonType.NUMBER, text = "5"),
        ButtonParams(ButtonType.NUMBER, text = "6"),
        ButtonParams(ButtonType.OPERRATION, text = "-")
    ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "1"),
        ButtonParams(ButtonType.NUMBER, text = "2"),
        ButtonParams(ButtonType.NUMBER, text = "3"),
        ButtonParams(ButtonType.OPERRATION, text = "+")
    ),
    listOf(
        ButtonParams(ButtonType.NUMBER, text = "0"),
        ButtonParams(ButtonType.DOT, text = "•"),
        ButtonParams(ButtonType.DELETE),
        ButtonParams(ButtonType.EQUAL, text = "=")
    )
)