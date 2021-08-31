package com.example.calculadora
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG="CalculadoraViewModel"
class CalculadoraViewModel: ViewModel() {
    private val modeloCalculadora = CalculatorModel()
    init {
        Log.d(TAG, "Instancia de viewmodel creada")
    }
    fun setOperando(unOperando: String) {
        modeloCalculadora.setOperation(unOperando)
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "Instancia destruida")
    }
}