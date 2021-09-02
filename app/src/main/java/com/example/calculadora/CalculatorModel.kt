package com.example.calculadora

import android.util.Log
import java.lang.Exception
import java.lang.Math.cos
import java.lang.Math.sin

class CalculatorModel {
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation = ""
    private var result = 0.0
    private var memory = 0.0

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
            "x^n" -> result = Math.pow(firstNumber, secondNumber)
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

    fun calculateSin(number: Double): Double {
        return sin(number)
    }

    fun calculateCos(number: Double): Double {
        return cos(number)
    }

    fun calculateSqrt(number: Double): Double {
        return Math.sqrt(number)
    }

    fun calculateInverse(number: Double): Double {
        return 1 / number
    }
    fun calculateTPow(number: Double): Double {
        return Math.pow(10.0, number)
    }

    fun setMemory(number: Double) {
        memory = number
    }
}