package com.example.calculadora

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var resultDisplay: TextView
    private lateinit var operationDisplay: TextView
    private lateinit var memDisplay: TextView
    private val calculator = CalculatorModel()
    private val calculadoraViewModel: CalculadoraViewModel by lazy {
        ViewModelProvider(this).get(CalculadoraViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultDisplay = findViewById(R.id.resultDisplay)
        operationDisplay = findViewById(R.id.operationDisplay)
        memDisplay = findViewById(R.id.memoria)
    }

    fun addNumberToDisplay(button: View) {
        var number = (button as Button ).text.toString()
        if(number == "π"){
            number = "3.1416"
        }

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
    @Suppress("UNUSED_PARAMETER")
    fun calcInverse(button: View){
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateInverse(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER")
    fun calcTPow(button: View){
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateTPow(number)
        resultDisplay.text = "$result"
    }

    @Suppress("UNUSED_PARAMETER")
    fun store(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        calculator.setMemory(number)
        memDisplay.text = "$number"
    }

    @Suppress("UNUSED_PARAMETER")
    fun recall(button: View) {
        try {
            var memory = calculator.getMemory()
            val splittedDisplay = resultDisplay.text.
            toString().split(calculator.getOperation()).toTypedArray()

            calculator.setSecondNumber(memory)
            if(splittedDisplay[0].toDouble() === 0.0){
                operationDisplay.text = "${memory}${calculator.getOperation()}$memory"
                calculator.setFirstNumber(memory)
                calculator.setSecondNumber(memory)
            }else{
                operationDisplay.text = "${splittedDisplay[0]}${calculator.getOperation()}$memory"
                calculator.setFirstNumber(splittedDisplay[0].toString().toDouble())
                calculator.setSecondNumber(memory)
            }
            resultDisplay.text = "$memory"
        }catch (error: Exception){

        }

    }

    @Suppress("UNUSED_PARAMETER")
    fun memPlus(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val memory = calculator.memPlus(number)
        memDisplay.text = "$memory"
    }

    @Suppress("UNUSED_PARAMETER")
    fun memMinus(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val memory = calculator.memMinus(number)
        memDisplay.text = "$memory"
    }
    @Suppress("UNUSED_PARAMETER")
    fun MC(button: View) {
        calculator.resetMemory()
        memDisplay.text = "0.0"
    }

    @Suppress("UNUSED_PARAMETER")
    fun clear(button: View) {
        calculator.resetCalculator()
        memDisplay.text = "0.0"
        operationDisplay.text = ""
        resultDisplay.text = "0.0"
    }
    @Suppress("UNUSED_PARAMETER")
    fun backspace(button: View) {
        try {
            val number = resultDisplay.text.toString()
            val newNumber = number.substring(0, number.length - 1)
            if(newNumber.length === 0)
                resultDisplay.text = "0.0"
            else
                resultDisplay.text = "$newNumber"
        }catch(e: Exception){
            resultDisplay.text = "0.0"
        }

    }
    @Suppress("UNUSED_PARAMETER")
    fun degrad(button: View) {
        Toast.makeText(applicationContext, "Cambio a Grados", Toast.LENGTH_SHORT).show()
    }
    override fun onStart() {
        super.onStart()
        //resultDisplay.text = calculadoraViewModel.resultado
    }
    override fun onDestroy() {
        super.onDestroy()
        //CalculadoraViewModel.resultado = "Lo que quiero poner en el display"
    }
}