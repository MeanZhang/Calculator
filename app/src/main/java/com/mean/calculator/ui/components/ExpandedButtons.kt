package com.mean.calculator.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mean.calculator.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedButtons(expanded: MutableState<Boolean>, viewModel: MainViewModel?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                textButtons[0].forEach {
                    TextCalculatorButton(it, viewModel, false)
                }
            }
            AnimatedVisibility(visible = expanded.value) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        textButtons[1].forEach {
                            TextCalculatorButton(it, viewModel, false)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        textButtons[2].forEach {
                            TextCalculatorButton(it, viewModel, false)
                        }
                    }
                }
            }
        }
        FilledTonalIconButton(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .padding(start = 8.dp)
                .size(36.dp),
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                    alpha = 0.3f
                )
            ),
            onClick = { expanded.value = !expanded.value }) {
            Icon(
                if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                "展开",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}


private val textButtons = listOf(
    listOf(
        "√", "π", "^", "!"
    ),
    listOf(
        "DEG", "sin", "cos", "tan"
    ),
    listOf(
        "INV", "e", "ln", "log"
    )
)