package com.mean.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.mariuszgromada.math.mxparser.Expression
import kotlin.math.abs
import kotlin.math.roundToInt

const val TAG = "MEANL"

class MainViewModel : ViewModel() {
    private val _expression = MutableStateFlow("")
    private val _result = MutableStateFlow("")
    private var leftBracketNum = 0

    val expression: StateFlow<String>
        get() = _expression
    val result: StateFlow<String>
        get() = _result

    private fun update() {
        val ex = Expression(_expression.value)
        val res = ex.calculate()
        Log.d(TAG, "ex: " + ex.expressionString)
        Log.d(TAG, "res: $res")
        if (res.isNaN()) {
            _result.value = ex.calculate().toString()
        } else {
            val int = res.roundToInt()
            if (abs(res - int) < 10e-5) {
                _result.value = int.toString()
            } else {
                _result.value = ex.calculate().toString()
            }
        }
    }

    private fun ac() {
        _expression.value = ""
        _result.value = ""
    }

    private fun calculate() {
        if (_result.value.isNotEmpty()) {
            _expression.value = _result.value
            _result.value = ""
        }
    }

    private fun delete() {
        if (_expression.value.isNotEmpty()) {
            _expression.value = _expression.value.dropLast(1)
            if (_expression.value.isNotEmpty()) {
                update()
            }
        }
    }

    private fun add(value: String?) {
        _expression.value += value
        update()
    }

    private fun brackets() {
        val last = _expression.value.takeLast(1)
        if (last.toIntOrNull() == null) {
            _expression.value += "("
            leftBracketNum++
        } else if (leftBracketNum > 0) {
            _expression.value += ")"
            leftBracketNum--
        } else {
            _expression.value += "("
            leftBracketNum++
        }
        update()
    }


    fun click(button: ButtonParams) {
        when (button.type) {
            ButtonType.AC -> ac()
            ButtonType.EQUAL -> calculate()
            ButtonType.DELETE -> delete()
            ButtonType.BRACKETS -> brackets()
            ButtonType.DOT -> _expression.value += "."
            else -> add(button.text)
        }
    }
}