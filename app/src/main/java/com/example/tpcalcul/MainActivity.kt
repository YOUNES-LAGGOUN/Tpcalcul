package com.example.tpcalcul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var donneN = ""
    private var operation = ""
    private var num01 = ""
    private var num02 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.btn0 to "0",
            R.id.btn1 to "1",
            R.id.btn2 to "2",
            R.id.btn3 to "3",
            R.id.btn4 to "4",
            R.id.btn5 to "5",
            R.id.btn6 to "6",
            R.id.btn7 to "7",
            R.id.btn8 to "8",
            R.id.btn9 to "9",
            R.id.btnp to "+",
            R.id.btnM to "-",
            R.id.btnmu to "*",
            R.id.btnD to "/"
        )

        buttons.forEach { (id, text) ->
            findViewById<Button>(id).setOnClickListener { buttonop(text) }
        }

        findViewById<Button>(R.id.btnC).setOnClickListener { vide() }
        findViewById<Button>(R.id.btnR).setOnClickListener { march() }
    }

    private fun buttonop(input: String) {
        if (input in listOf("+", "-", "*", "/")) {
            setOperator(input)
        } else {
            numadd(input)
        }
    }

    private fun numadd(number: String) {
        donneN += number
        display.text = donneN
    }

    private fun setOperator(op: String) {
        if (donneN.isNotEmpty()) {
            num01 = donneN
            operation = op
            donneN = ""
        }
    }

    private fun vide() {
        donneN = ""
        operation = ""
        num01 = ""
        display.text = "0"
    }

    private fun march() {
        if (donneN.isNotEmpty() && num01.isNotEmpty() && operation.isNotEmpty()) {
            num02 = donneN
            val result = calcul(num01, num02, operation)
            display.text = if (result.isNaN()) "Error" else result.toString()
            donneN = result.toString()
            num01 = ""
            operation = ""
        }
    }

    private fun calcul(num1: String, num2: String, op: String): Double {
        return try {
            val n1 = num1.toDouble()
            val n2 = num2.toDouble()
            when (op) {
                "+" -> n1 + n2
                "-" -> n1 - n2
                "*" -> n1 * n2
                "/" -> n1 / n2
                else -> Double.NaN
            }
        } catch (e: NumberFormatException) {
            Double.NaN
        }
    }
}
