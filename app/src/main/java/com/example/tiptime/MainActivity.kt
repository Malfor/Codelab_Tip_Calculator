package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()

        if (cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }

        val tipPercentage = calculateTipPercentage()
        val tip = calculateTipCost(cost, tipPercentage)

        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun calculateTipCost(cost: Double, tipPercentage: Double): Double {
        var tip = cost * tipPercentage
        if (binding.roundUpSwitch.isChecked){
            tip = ceil(tip)
        }
        return tip
    }

    private fun calculateTipPercentage(): Double {
        return when(binding.tipOpcion.checkedRadioButtonId){
            R.id.option_twenty_percental -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
    }


}