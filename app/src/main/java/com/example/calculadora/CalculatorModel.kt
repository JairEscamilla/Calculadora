package com.example.calculadora

import java.lang.Exception

class CalculatorModel {
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation = ""
    private var result = 0.0;

    fun setFirstNumber(number: Double) {
        firstNumber = number
    }

    fun setSecondNumber(number: Double) {
        secondNumber = number
    }

    fun setOperation(operand: String) {
        operation = operand
    }

    fun getFirstNumber(): Double {
        return firstNumber
    }

    fun getSecondNumber(): Double {
        return secondNumber
    }

    fun getOperation(): String {
        return operation
    }
    private fun calculateResult() {
        when(operation) {
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
            "x" -> result = firstNumber * secondNumber
            "÷" -> result = firstNumber / secondNumber
        }
    }
    fun getResult(): Double {
        try {
            calculateResult()
        }catch (e: Exception){
            resetValues()
        }
        return result
    }

    private fun resetValues() {
        result = 0.0
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""
    }
}