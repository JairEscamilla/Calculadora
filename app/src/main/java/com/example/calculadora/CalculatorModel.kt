package com.example.calculadora

import android.util.Log
import android.widget.TextView
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
    // Getters y setters
    fun setFirstNumber(number: Double) {
        firstNumber = number
    }

    fun setMode(nmode: String) {
        mode = nmode
    }

    fun setResult(number: Double) {
        result = number
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
    fun getMode(): String {
        return mode
    }
    // Funcion para obtener resultados
    private fun calculateResult() {
        when(operation) {
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
            "x" -> result = firstNumber * secondNumber
            "÷" -> result = firstNumber / secondNumber
            "x^n" -> result = Math.pow(firstNumber, secondNumber)
        }
    }
    // Ejecuta la funcion que obtiene los resultados
    fun getResult(): Double {
        try {
            calculateResult()
        }catch (e: Exception){
            resetValues()
        }
        return result
    }
    // Reseteo la calculadora
    private fun resetValues() {
        result = 0.0
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""
    }
    // Calculo el seno
    fun calculateSin(number: Double): Double {
        return sin(getArgument(number))
    }
    // Calculo el coseno
    fun calculateCos(number: Double): Double {
        return cos(getArgument(number))
    }
    // Calculo raiz cuadrada
    fun calculateSqrt(number: Double): Double {
        return Math.sqrt(number)
    }
    // Calculadora inverso
    fun calculateInverse(number: Double): Double {
        return 1 / number
    }
    // Calculo la potencio de un numero
    fun calculateTPow(number: Double): Double {
        return Math.pow(10.0, number)
    }
    // Seteo la memoria
    fun setMemory(number: Double) {
        memory = number
    }
    // Obtengo la memoria
    fun getMemory(): Double {
        return memory
    }
    // Sumo a la memoria
    fun memPlus(number: Double): Double {
        val newMemory = memory + number
        memory = newMemory
        return memory
    }
    // Resto a la memoria
    fun memMinus(number: Double): Double {
        val newMemory = memory - number
        memory = newMemory
        return memory
    }
    // Reseteo la memoria
    fun resetMemory() {
        memory = 0.0
    }
    // Reseteo la calculadora
    fun resetCalculator() {
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""
        result = 0.0
        memory = 0.0
    }
    // Obtengo argumentos para el modo de calculadora
    fun getArgument(angle: Double): Double {
        if(mode == "grados")
            return Math.toRadians(angle)
        return angle
    }
    // Cambio el modo
    fun changeMode(): String {
        if(mode == "grados")
            mode = "radianes"
        else
            mode = "grados"

        return mode
    }
}