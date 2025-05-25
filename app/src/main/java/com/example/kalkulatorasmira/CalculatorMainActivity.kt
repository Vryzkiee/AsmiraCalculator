package com.example.kalkulatorasmira

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.kalkulatorasmira.databinding.ActivityMainBinding




class CalculatorMainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengamati input dan hasil dari ViewModel
        viewModel.input.observe(this, Observer { input ->
            binding.tvFormula.text = input
        })

        viewModel.result.observe(this, Observer { result ->
            binding.tvResult.text = result
        })

        // Menambahkan aksi ke tombol angka
        val numberButtons = listOf(
            binding.zero, binding.one, binding.two, binding.three, binding.four,
            binding.five, binding.six, binding.seven, binding.eight, binding.nine
        )
        for (i in numberButtons.indices) {
            numberButtons[i].setOnClickListener {
                viewModel.appendToInput(i.toString())
            }
        }

        // Tombol koma (titik)
        binding.dot.setOnClickListener {
            viewModel.appendToInput(".")
        }

        // Aksi untuk tombol operator
        binding.plus.setOnClickListener { viewModel.handleOperator('+') }
        binding.mines.setOnClickListener { viewModel.handleOperator('-') }
        binding.multiply.setOnClickListener { viewModel.handleOperator('*') }
        binding.devide.setOnClickListener { viewModel.handleOperator('/') }

        // Tombol hasil "="
        binding.equal.setOnClickListener { viewModel.calculateResult() }

        // Tombol delete "DEL"
        binding.clear.setOnClickListener { viewModel.clearInput() }

    }
}