package com.mean.calculator

data class ButtonParams(val type: ButtonType, val text: String? = null)

enum class ButtonType {
    NUMBER, OPERRATION, AC, DELETE, EQUAL, DOT, BRACKETS
}