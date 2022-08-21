package com.mean.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.mean.calculator.ui.components.CalculatorButton
import com.mean.calculator.ui.components.TextCalculatorButton
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DisplayArea(viewModel)
        OperationArea(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.DisplayArea(viewModel: MainViewModel) {
    Card(
        shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
        modifier = Modifier
            .weight(1F, true)
            .fillMaxWidth()
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            ) {
                Icon(Icons.Default.MoreVert, contentDescription = "更多选项")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                val expression by viewModel.expression.collectAsState()
                val result by viewModel.result.collectAsState()
                Text(
                    expression,
                    fontSize = 72.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    result,
                    fontSize = 48.sp,
                    textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth().padding(top=8.dp),
                )
            }
        }
    }
}


@Composable
fun ColumnScope.OperationArea(viewModel: MainViewModel) {
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
                    TextCalculatorButton(it, viewModel)
                }
            }
            AnimatedVisibility(visible = expanded.value) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        textButtons[1].forEach {
                            TextCalculatorButton(it, viewModel)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        textButtons[2].forEach {
                            TextCalculatorButton(it, viewModel)
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

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    CalculatorTheme {
        Content(MainViewModel())
    }
}