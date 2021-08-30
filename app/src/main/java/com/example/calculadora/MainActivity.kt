package com.example.calculadora

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var resultDisplay: TextView
    private lateinit var operationDisplay: TextView
    private val calculator = CalculatorModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultDisplay = findViewById(R.id.resultDisplay)
        operationDisplay = findViewById(R.id.operationDisplay)
    }

    fun addNumberToDisplay(button: View) {
        val number = (button as Button ).text.toString()
        val displayValue = operationDisplay.text.toString()
        val newDisplayValue = displayValue + number
        operationDisplay.text = newDisplayValue
        try {

            if (calculator.getOperation() == "") {
                calculator.setFirstNumber(newDisplayValue.toDouble())
            }else {
                val splittedDisplay = newDisplayValue.split(calculator.getOperation()).toTypedArray()
                calculator.setSecondNumber(splittedDisplay[1].toDouble())
            }
        } catch (e: Exception) {

        }
    }

    fun addOperation(button: View) {
        val operation = (button as Button).text.toString()
        calculator.setOperation(operation)
        val newDisplayValue = operationDisplay.text.toString() + operation
        operationDisplay.text = newDisplayValue
    }
    @Suppress("UNUSED_PARAMETER")
    fun getResult(button: View) {
        val result = calculator.getResult()
        resultDisplay.text = result.toString()
    }
}