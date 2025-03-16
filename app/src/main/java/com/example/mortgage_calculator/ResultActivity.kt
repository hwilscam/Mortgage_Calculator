package com.example.mortgage_calculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mortgage_calculator.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val monthly_payment_output = findViewById<TextView>(R.id.monthly_payment_text)
        val total_payment_output = findViewById<TextView>(R.id.total_payment_text)

        val principal = intent.getDoubleExtra("PRINCIPAL", 0.0)
        val interestRate = intent.getDoubleExtra("INTEREST_RATE", 0.0)
        val loanTerm = intent.getIntExtra("LOAN_TERM", 0)

        if (principal > 0 && interestRate > 0 && loanTerm > 0) {
            val monthlyInterestRate = (interestRate / 100) / 12
            val numPayments = loanTerm * 12

            val monthlyPayment = (principal * monthlyInterestRate) /
                    (1 - (1 + monthlyInterestRate).pow(-numPayments))

            val totalPayment = monthlyPayment * numPayments

            val toDecimal = DecimalFormat("#.##")
            monthly_payment_output.text = toDecimal.format(monthlyPayment)
            total_payment_output.text = toDecimal.format(totalPayment)

            val resultIntent = Intent().apply {

                putExtra("MONTHLY_PAYMENT", toDecimal.format(monthlyPayment))
                putExtra("TOTAL_PAYMENT", toDecimal.format(totalPayment))

            }

            setResult(RESULT_OK, resultIntent)
            finish()

        }
    }
}
