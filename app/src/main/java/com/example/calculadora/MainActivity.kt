package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var resultDisplay: TextView
    private lateinit var operationDisplay: TextView
    private val calculator = CalculatorModel()
    private val calculadoraViewModel: CalculadoraViewModel by lazy {
        ViewModelProvider(this).get(CalculadoraViewModel::class.java)
    }

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
                resultDisplay.text = newDisplayValue
            }else {
                val splittedDisplay = newDisplayValue.split(calculator.getOperation()).toTypedArray()
                resultDisplay.text = splittedDisplay[1]
                calculator.setSecondNumber(splittedDisplay[1].toDouble())
            }
        } catch (e: Exception) {

        }
    }

    fun addOperation(button: View) {
        val operation = (button as Button).text.toString()
        var newDisplayValue = operationDisplay.text.toString() + operation
        val result = calculator.getResult()
        if(calculator.getOperation() != ""){
            calculator.setFirstNumber(result)
            calculator.setSecondNumber(0.0)
            newDisplayValue = "${calculator.getFirstNumber()}$operation"
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
    @Suppress("UNUSED_PARAMETER")
    fun changeSign(button: View) {
        val newNumber = (resultDisplay.text.toString().toDouble() * -1)
        val splittedDisplay = operationDisplay.text.split(calculator.getOperation()).toTypedArray()
        if(calculator.getOperation()  == ""){
            operationDisplay.text = "$newNumber"
            calculator.setFirstNumber(newNumber)
        }else{
            operationDisplay.text = "${splittedDisplay[0]}${calculator.getOperation()}$newNumber"
            calculator.setSecondNumber(newNumber)
        }
        resultDisplay.text = "$newNumber"
    }
    @Suppress("UNUSED_PARAMETER")
    fun calcSin(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateSin(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER")
    fun calcCos(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateCos(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER")
    fun calcSqrt(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateSqrt(number)
        resultDisplay.text = "$result"
    }
}