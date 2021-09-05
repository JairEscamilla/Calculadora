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
    private var mode = "radianes"

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
        return sin(getArgument(number))
    }

    fun calculateCos(number: Double): Double {
        return cos(getArgument(number))
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

    fun getMemory(): Double {
        return memory
    }
    fun memPlus(number: Double): Double {
        val newMemory = memory + number
        memory = newMemory
        return memory
    }
    fun memMinus(number: Double): Double {
        val newMemory = memory - number
        memory = newMemory
        return memory
    }
    fun resetMemory() {
        memory = 0.0
    }
    fun resetCalculator() {
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""
        result = 0.0
        memory = 0.0
    }
    fun getArgument(angle: Double): Double {
        if(mode == "grados")
            return Math.toRadians(angle)
        return angle
    }
    fun changeMode(): String {
        if(mode == "grados")
            mode = "radianes"
        else
            mode = "grados"

        return mode
    }
}