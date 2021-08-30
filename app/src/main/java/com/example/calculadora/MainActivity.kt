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
        var newDisplayValue = ""
        val result = calculator.getResult()
        if(calculator.getOperation() != ""){
            calculator.setFirstNumber(result)
            calculator.setSecondNumber(0.0)
            newDisplayValue = "${calculator.getFirstNumber()}$operation"
        }else {
            newDisplayValue = operationDisplay.text.toString() + operation
        }
        calculator.setOperation(operation)
        operationDisplay.text = newDisplayValue
        resultDisplay.text = result.toString()
    }
    @Suppress("UNUSED_PARAMETER")
    fun getResult(button: View) {
        val result = calculator.getResult()
        resultDisplay.text = result.toString()
    }
}