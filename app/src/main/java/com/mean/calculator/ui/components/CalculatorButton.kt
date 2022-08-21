package com.mean.calculator.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mean.calculator.ButtonParams
import com.mean.calculator.ButtonType
import com.mean.calculator.MainViewModel

@Composable
fun CalculatorButton(
    button: ButtonParams,
    expanded: Boolean,
    viewModel: MainViewModel? = null,
) {
    val animatedHeightState = animateDpAsState(targetValue = if (expanded) 76.dp else 90.dp)
    val animatedFloatState = animateFloatAsState(targetValue = if (expanded) 1.2f else 1f)
    Surface(
        modifier = Modifier
            .width(90.dp)
            .height(animatedHeightState.value)
            .clip(CircleShape)
            .clickable { viewModel?.click(button) },
        color = when (button.type) {
            ButtonType.EQUAL -> MaterialTheme.colorScheme.primaryContainer
            ButtonType.AC -> MaterialTheme.colorScheme.tertiaryContainer
            ButtonType.OPERRATION, ButtonType.BRACKETS -> MaterialTheme.colorScheme.secondaryContainer
            else -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        }
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (button.text !== null) {
                Text(
                    button.text,
                    fontSize = (if (button.type == ButtonType.OPERRATION) 48.sp else 40.sp) / animatedFloatState.value,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            } else {
                Icon(
                    Icons.Default.Backspace,
                    "删除",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

//@Composable
//fun CalculatorButton(
//    button: ButtonParams,
//    onClick: () -> Unit,
//    size: Dp,
//    content: @Composable() (RowScope.() -> Unit),
//) {
//    FilledTonalButton(
////        shape = CircleShape,
//        onClick = { onClick() },
//        modifier = Modifier
//            .size(size),
//        colors = ButtonDefaults.filledTonalButtonColors(
//            containerColor = when (button.type) {
//                ButtonType.EQUAL -> MaterialTheme.colorScheme.primaryContainer
//                ButtonType.AC -> MaterialTheme.colorScheme.tertiaryContainer
//                ButtonType.OPERRATION -> MaterialTheme.colorScheme.secondaryContainer
//                else -> MaterialTheme.colorScheme.inverseOnSurface
//            }
//        ),
//        content = content
//    )
//}
