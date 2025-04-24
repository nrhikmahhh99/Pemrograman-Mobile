package com.example.modul2

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private val viewModel: TipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val costOfServiceEditText = findViewById<EditText>(R.id.edit_cost_of_service)
        val tipOptions = findViewById<RadioGroup>(R.id.tip_options)
        val roundUpSwitch = findViewById<Switch>(R.id.switch_round_up)
        val calculateButton = findViewById<Button>(R.id.button_calculate)
        val tipResultTextView = findViewById<TextView>(R.id.text_tip_result)

        tipResultTextView.text = viewModel.tipResult

        calculateButton.setOnClickListener {
            val costInput = costOfServiceEditText.text.toString()
            val cost = costInput.toDoubleOrNull()

            if (costInput.isEmpty()) {
                tipResultTextView.text = ""
                Toast.makeText(this, "Input tidak boleh kosong. Harap masukkan angka!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (cost == null || cost <= 0.0) {
                tipResultTextView.text = ""
                Toast.makeText(this, "Harap masukkan angka yang valid dan lebih dari 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipPercentage = when (tipOptions.checkedRadioButtonId) {
                R.id.radio_amazing -> 0.20
                R.id.radio_good -> 0.18
                else -> 0.15
            }
            var tip = cost * tipPercentage

            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

            viewModel.tipResult = "Tip Amount: $formattedTip"
            tipResultTextView.text = viewModel.tipResult
        }
    }
}
