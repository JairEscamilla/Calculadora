package com.example.calculadora
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel

private const val TAG="CalculadoraViewModel"
class CalculadoraViewModel: ViewModel() {
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation = ""
    private var result = 0.0
    private var memory = 0.0
    private var mode = "radianes"
    private var resultDisplay = ""
    private var operationDisplay = ""
    private var memDisplay = "0"
    private var modeDisplay = "radianes"

    init {
        Log.d(TAG, "Instancia de viewmodel creada")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "Instancia destruida")
    }
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
    fun getResult(): Double {
        return result
    }
    fun setResult(number: Double) {
        result = number
    }
    fun getMemory(): Double {
        return memory
    }
    fun setMemory(number: Double) {
        memory = number
    }
    fun getMode(): String {
        return mode
    }
    fun setMode(cmode: String) {
        mode = cmode
    }
    fun getResultDisply(): String {
        return resultDisplay
    }
    fun setResultDisplay(text: String) {
        resultDisplay = text
    }
    fun getOperationDisplay(): String {
        return operationDisplay
    }
    fun setOperationDisplay(text: String) {
        operationDisplay = text
    }
    fun getMemDisplay(): String {
        return memDisplay
    }
    fun setMemDisplay(text: String) {
        memDisplay = text
    }
    fun getModeDisplay(): String {
        return modeDisplay
    }
    fun setModeDisplay(text: String){
        modeDisplay = text
    }
}