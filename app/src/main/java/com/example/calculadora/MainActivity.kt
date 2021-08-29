package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var displayResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayResult = findViewById(R.id.resultDisplay)
    }

    fun addNumberToDisplay(button: View) {
        val number = (button as Button ).text.toString()
        displayResult.text = number
    }
}