package com.example.calculatorapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var isMultiplication = false
    private var isDivision = false
    private var isAddition = false
    private var isSubtraction = false
    private var input: String = ""
    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var _result: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initWidget()
    }

    @SuppressLint("SetTextI18n")
    private fun initWidget() {
        binding.btnOne.setOnClickListener {
            binding.tvResult.text = "${input}1"
            input = binding.tvResult.text.toString()
        }
        binding.btnTwo.setOnClickListener {
            binding.tvResult.text = "${input}2"
            input = binding.tvResult.text.toString()
        }
        binding.btnThree.setOnClickListener {
            binding.tvResult.text = "${input}3"
            input = binding.tvResult.text.toString()
        }
        binding.btnFour.setOnClickListener {
            binding.tvResult.text = "${input}4"
            input = binding.tvResult.text.toString()
        }
        binding.btnFive.setOnClickListener {
            binding.tvResult.text = "${input}5"
            input = binding.tvResult.text.toString()
        }
        binding.btnSix.setOnClickListener {
            binding.tvResult.text = "${input}6"
            input = binding.tvResult.text.toString()
        }
        binding.btnSeven.setOnClickListener {
            binding.tvResult.text = "${input}7"
            input = binding.tvResult.text.toString()
        }
        binding.btnEight.setOnClickListener {
            binding.tvResult.text = "${input}8"
            input = binding.tvResult.text.toString()
        }
        binding.btnNice.setOnClickListener {
            binding.tvResult.text = "${input}9"
            input = binding.tvResult.text.toString()
        }
        binding.btnZero.setOnClickListener {
            if (input != "") {
                if (input.substring(0, 1) != "0") {
                    binding.tvResult.text = "${input}0"
                    input = binding.tvResult.text.toString()
                }
            }else{
                binding.tvResult.text = "${input}0"
                input = binding.tvResult.text.toString()
            }
        }
        binding.btnComma.setOnClickListener {
            if (input != "" && !input.contains(".")) {
                binding.tvResult.text = "${input}."
                input = binding.tvResult.text.toString()
            }
        }

        binding.btnSign.setOnClickListener {
            calculator()
        }
        binding.btnAc.setOnClickListener {
            binding.tvResult.text = ""
            binding.tvCalculation.text = ""
            input = ""
            _result = 0.0
            isDivision = false
            isAddition = false
            isMultiplication = false
            isSubtraction = false
        }
        binding.btnLunisolar.setOnClickListener {
            if (input != "") {
                input = if (input.toDouble() > 0) {
                    "-$input"
                } else {
                    input.replace("-", "")
                }
                binding.tvResult.text = input
            }
        }
        binding.btnBack.setOnClickListener {
            if (input != "") {
                if (input.lastIndex == 1 && input.contains("-")) {
                    input = ""
                    binding.tvResult.text = input
                } else {
                    input = input.substring(0, input.lastIndex)
                    binding.tvResult.text = input
                }
            }
        }

        binding.btnDivision.setOnClickListener {
            if (isSubtraction || isAddition || isMultiplication || isDivision) {
                calculatorInfinity()
                binding.tvCalculation.text = "$firstNumber / "
                isDivision = true
            } else {
                if (input != "") {
                    isDivision = true
                    firstNumber = input.toDouble()
                    binding.tvCalculation.text = "$firstNumber /"
                    binding.tvResult.text = ""
                    input = ""
                }
            }
            binding.btnComma.isEnabled = true
        }
        binding.btnAddition.setOnClickListener {
            if (isSubtraction || isAddition || isMultiplication || isDivision) {
                calculatorInfinity()
                binding.tvCalculation.text = "$firstNumber + "
                isAddition = true
            } else {
                if (input != "") {
                    isAddition = true
                    firstNumber = input.toDouble()
                    binding.tvCalculation.text = "$firstNumber +"
                    binding.tvResult.text = ""
                    input = ""
                }
            }
            binding.btnComma.isEnabled = true
        }
        binding.btnSubtraction.setOnClickListener {
            if (isSubtraction || isAddition || isMultiplication || isDivision) {
                calculatorInfinity()
                binding.tvCalculation.text = "$firstNumber - "
                isSubtraction = true
            } else {
                if (input != "") {
                    isSubtraction = true
                    firstNumber = input.toDouble()
                    binding.tvCalculation.text = "$firstNumber -"
                    binding.tvResult.text = ""
                    input = ""
                }
            }
            binding.btnComma.isEnabled = true
        }
        binding.btnMultiplication.setOnClickListener {
            if (isSubtraction || isAddition || isMultiplication || isDivision) {
                calculatorInfinity()
                binding.tvCalculation.text = "$firstNumber * "
                isMultiplication = true
            } else {
                if (input != "") {
                    isMultiplication = true
                    firstNumber = input.toDouble()
                    binding.tvCalculation.text = "$firstNumber * "
                    binding.tvResult.text = ""
                    input = ""
                }
            }
            binding.btnComma.isEnabled = true
        }
    }

    private fun calculator() {
        when {
            isAddition -> {
                secondNumber = input.toDouble()
                _result = firstNumber + secondNumber
                binding.tvCalculation.text = "$firstNumber + $secondNumber ="
            }
            isSubtraction -> {
                secondNumber = input.toDouble()
                _result = firstNumber - secondNumber
                binding.tvCalculation.text = "$firstNumber - $secondNumber ="
            }
            isMultiplication -> {
                secondNumber = input.toDouble()
                _result = firstNumber * secondNumber
                binding.tvCalculation.text = "$firstNumber * $secondNumber ="
            }
            isDivision -> {
                secondNumber = input.toDouble()
                _result = firstNumber / secondNumber
                binding.tvCalculation.text = "$firstNumber / $secondNumber ="
            }
        }
        isDivision = false
        isAddition = false
        isMultiplication = false
        isSubtraction = false
        binding.tvResult.text = "$_result"
        input = _result.toString()
    }

    private fun calculatorInfinity() {
        if (input != "") {
            when {
                isDivision -> {
                    isDivision = false
                    isAddition = false
                    isMultiplication = false
                    isSubtraction = false
                    firstNumber /= input.toDouble()
                    binding.tvResult.text = ""
                    input = ""
                }
                isMultiplication -> {
                    isDivision = false
                    isAddition = false
                    isMultiplication = false
                    isSubtraction = false
                    firstNumber *= input.toDouble()
                    binding.tvResult.text = ""
                    input = ""
                }
                isAddition -> {
                    isDivision = false
                    isAddition = false
                    isMultiplication = false
                    isSubtraction = false
                    firstNumber += input.toDouble()
                    binding.tvResult.text = ""
                    input = ""
                }
                isSubtraction -> {
                    isDivision = false
                    isAddition = false
                    isMultiplication = false
                    isSubtraction = true
                    firstNumber -= input.toDouble()
                    binding.tvResult.text = ""
                    input = ""
                }
            }
        }
    }
}