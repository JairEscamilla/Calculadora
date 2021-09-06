package com.example.calculadora

import android.os.Bundle
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
    private lateinit var modeDisplay: TextView
    // View Model de la calculadora
    private val calculadoraViewModel: CalculadoraViewModel by lazy {
        ViewModelProvider(this).get(CalculadoraViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) { // Al crear la pantalla obteng los valores
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultDisplay = findViewById(R.id.resultDisplay)
        operationDisplay = findViewById(R.id.operationDisplay)
        memDisplay = findViewById(R.id.memoria)
        modeDisplay = findViewById(R.id.mode)
    }
    // A esta funcion agregamos un nuevo numero en el display de
    fun addNumberToDisplay(button: View) {
        var number = (button as Button ).text.toString()
        if(number == "π"){
            number = "3.1416"
        }

        val displayValue = operationDisplay.text.toString()
        val newDisplayValue = displayValue + number
        operationDisplay.text = newDisplayValue
        try { // Agrego la operacion al primer numero o al segundo de acuerdo al operador
            if (calculator.getOperation() == "") {
                calculator.setFirstNumber(newDisplayValue.toDouble())
                resultDisplay.text = newDisplayValue
            }else {
                val splittedDisplay = newDisplayValue.split(calculator.getOperation()).toTypedArray()
                resultDisplay.text = splittedDisplay[1]
                calculator.setSecondNumber(splittedDisplay[1].toDouble())
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ocurrio un error", Toast.LENGTH_SHORT).show()
        }
    }

    fun addOperation(button: View) { // Agrego operacion al display
        val operation = (button as Button).text.toString()
        var newDisplayValue = operationDisplay.text.toString() + operation
        val result = calculator.getResult()
        if(calculator.getOperation() != ""){ // Si no hay operacion, seteo el primer numero
            calculator.setFirstNumber(result)
            calculator.setSecondNumber(0.0)
            newDisplayValue = "${calculator.getFirstNumber()}$operation"
        }
        calculator.setOperation(operation)
        operationDisplay.text = newDisplayValue
        resultDisplay.text = result.toString()
    }
    @Suppress("UNUSED_PARAMETER") // Obtengo el resultado de la operacionn
    fun getResult(button: View) {
        val result = calculator.getResult()
        resultDisplay.text = result.toString()
    }
    @Suppress("UNUSED_PARAMETER")
    fun changeSign(button: View) { // Funcion para cambiar el signo del resultado
        val newNumber = (resultDisplay.text.toString().toDouble() * -1)
        val splittedDisplay = operationDisplay.text.split(calculator.getOperation()).toTypedArray()
        if(calculator.getOperation()  == ""){ // Si hay operacion, seteo el perimer numero
            operationDisplay.text = "$newNumber"
            calculator.setFirstNumber(newNumber)
        }else{
            operationDisplay.text = "${splittedDisplay[0]}${calculator.getOperation()}$newNumber"
            calculator.setSecondNumber(newNumber)
        }
        resultDisplay.text = "$newNumber"
    }
    @Suppress("UNUSED_PARAMETER")// Funcion para calcular el seno
    fun calcSin(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateSin(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER") // Funcion para calcular el coseno
    fun calcCos(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateCos(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER") // Funcion para calcular la raiz cuadrada
    fun calcSqrt(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateSqrt(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER" ) // Funcion para calcular el inverso
    fun calcInverse(button: View){
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateInverse(number)
        resultDisplay.text = "$result"
    }
    @Suppress("UNUSED_PARAMETER") // Funcion para calcular la potencia
    fun calcTPow(button: View){
        val number = resultDisplay.text.toString().toDouble()
        val result = calculator.calculateTPow(number)
        resultDisplay.text = "$result"
    }

    @Suppress("UNUSED_PARAMETER") // Funcion para agregar a la memoria
    fun store(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        calculator.setMemory(number)
        memDisplay.text = "$number"
    }

    @Suppress("UNUSED_PARAMETER") // Funcion para recall
    fun recall(button: View) {
        try {
            val memory = calculator.getMemory()
            val splittedDisplay = resultDisplay.text.
            toString().split(calculator.getOperation()).toTypedArray()

            calculator.setSecondNumber(memory)
            if(splittedDisplay[0].toDouble() == 0.0){ // En caso de ser 0, seteo un valo en el display o si no otro valor
                operationDisplay.text = "${memory}${calculator.getOperation()}$memory"
                calculator.setFirstNumber(memory)
                calculator.setSecondNumber(memory)
            }else{
                operationDisplay.text = "${splittedDisplay[0]}${calculator.getOperation()}$memory"
                calculator.setFirstNumber(splittedDisplay[0].toDouble())
                calculator.setSecondNumber(memory)
            }
            resultDisplay.text = "$memory"
        }catch (error: Exception){
            Toast.makeText(applicationContext, "Ocurrio un error", Toast.LENGTH_SHORT).show()
        }

    }

    @Suppress("UNUSED_PARAMETER")
    fun memPlus(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val memory = calculator.memPlus(number)
        memDisplay.text = "$memory"
    }

    @Suppress("UNUSED_PARAMETER") // Funcion para restar a la memoria
    fun memMinus(button: View) {
        val number = resultDisplay.text.toString().toDouble()
        val memory = calculator.memMinus(number)
        memDisplay.text = "$memory"
    }
    @Suppress("UNUSED_PARAMETER") // Funcion para resetear la memoria
    fun mC(button: View) {
        calculator.resetMemory()
        memDisplay.text = "0.0"
    }

    @Suppress("UNUSED_PARAMETER") // Funcion para limpiar
    fun clear(button: View) {
        calculator.resetCalculator()
        memDisplay.text = "0.0"
        operationDisplay.text = ""
        resultDisplay.text = "0.0"
    }
    @Suppress("UNUSED_PARAMETER") // Funcion para eliminar un numero en caso de que se haya equivocado el usuario
    fun backspace(button: View) {
        try {
            val number = resultDisplay.text.toString()
            val newNumber = number.substring(0, number.length - 1)
            calculator.setResult(newNumber.toDouble())

            try {
                val splittedDisplay = operationDisplay.text.
                toString().split(calculator.getOperation()).toTypedArray()
                if(calculator.getOperation() == ""){
                    calculator.setFirstNumber(newNumber.toDouble())
                    operationDisplay.text=newNumber
                }else{
                    calculator.setSecondNumber(newNumber.toDouble())
                    operationDisplay.text = "${splittedDisplay[0]}${calculator.getOperation()}$newNumber"
                }
            }catch (e: Exception) {
                Toast.makeText(applicationContext, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
            if(newNumber.isEmpty())
                resultDisplay.text = "0.0"
            else
                resultDisplay.text = newNumber
        }catch(e: Exception){
            resultDisplay.text = "0.0"
        }

    }
    @Suppress("UNUSED_PARAMETER") // Funcion para cambiar el modo de la calculadora
    fun degrad(button: View) {
        val mode = calculator.changeMode()
        modeDisplay.text = mode
        Toast.makeText(applicationContext, "Cambio a $mode", Toast.LENGTH_SHORT).show()
    }
    override fun onStart() { // Al iniciar la calculadora, seteo los nuevos valores (evito que se pierdan al voltear pantalla)
        super.onStart()
        calculator.setFirstNumber(calculadoraViewModel.getFirstNumber())
        calculator.setSecondNumber(calculadoraViewModel.getSecondNumber())
        calculator.setMemory(calculadoraViewModel.getMemory())
        calculator.setMode(calculadoraViewModel.getMode())
        calculator.setOperation(calculadoraViewModel.getOperation())
        calculator.setResult(calculadoraViewModel.getResult())
        memDisplay.text = calculadoraViewModel.getMemDisplay()
        modeDisplay.text = calculadoraViewModel.getMemDisplay()
        resultDisplay.text = calculadoraViewModel.getResultDisply()
        operationDisplay.text = calculadoraViewModel.getOperationDisplay()
    }
    override fun onDestroy() { // Al destruir la pantalla, seteo al viewmodel los respectivos valores
        super.onDestroy()
        calculadoraViewModel.setFirstNumber(calculator.getFirstNumber())
        calculadoraViewModel.setSecondNumber(calculator.getSecondNumber())
        calculadoraViewModel.setMemory(calculator.getMemory())
        calculadoraViewModel.setMode(calculator.getMode())
        calculadoraViewModel.setOperation(calculator.getOperation())
        calculadoraViewModel.setResult(calculator.getResult())
        calculadoraViewModel.setMemDisplay(memDisplay.text.toString())
        calculadoraViewModel.setModeDisplay(modeDisplay.text.toString())
        calculadoraViewModel.setResultDisplay(resultDisplay.text.toString())
        calculadoraViewModel.setOperationDisplay(operationDisplay.text.toString())
    }
}