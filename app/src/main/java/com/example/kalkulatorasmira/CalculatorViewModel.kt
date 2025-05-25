package com.example.kalkulatorasmira


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    // LiveData untuk menyimpan input dan hasil
    private val _input = MutableLiveData<String>()
    val input: LiveData<String> get() = _input

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    private var currentInput: String = ""
    private var lastOperator: Char? = null
    private var number1: Double? = null

    // Fungsi untuk menambahkan input
    fun appendToInput(value: String) {
        currentInput += value
        _input.value = currentInput
    }

    // Fungsi untuk menangani operator
    fun handleOperator(operator: Char) {
        if (currentInput.isNotEmpty()) {
            number1 = currentInput.toDoubleOrNull()
            currentInput = ""
            lastOperator = operator
        }
    }

    // Fungsi untuk menghitung hasil
    fun calculateResult() {
        if (lastOperator != null && currentInput.isNotEmpty()) {
            val number2 = currentInput.toDoubleOrNull()

            if (number1 != null && number2 != null) {
                val resultValue = when (lastOperator) {
                    '+' -> number1!! + number2
                    '-' -> number1!! - number2
                    '*' -> number1!! * number2
                    '/' -> if (number2 != 0.0) number1!! / number2 else null
                    else -> null
                }

                _result.value = resultValue?.toString() ?: "Tidak dapat melakukan operasi"
                clearCalculation()
            }
        }
    }

    // Fungsi untuk menghitung persen
    fun calculatePercentage() {
        if (currentInput.isNotEmpty()) {
            val number = currentInput.toDoubleOrNull()
            if (number != null) {
                val persen = number / 100
                currentInput = persen.toString()
                _input.value = currentInput
            } else {
                _input.value = "Masukkan angka yang valid"
            }
        }
    }

    // Fungsi untuk menghapus input
    fun clearInput() {
        currentInput = ""
        _input.value = ""
        clearCalculation()
    }

    // Fungsi untuk menghapus perhitungan
    private fun clearCalculation() {
        number1 = null
        lastOperator = null
    }

}