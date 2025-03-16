package com.example.mortgage_calculator

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mortgage_calculator.databinding.ActivityMainBinding

@Suppress("DEPRECATION")

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputPrincipal = findViewById<EditText>(R.id.principal_text)
        val inputInterest = findViewById<EditText>(R.id.interest_text)
        val inputLoan = findViewById<EditText>(R.id.loan_text)

        binding.calculateButton.setOnClickListener {
            val principal = inputPrincipal.text.toString().toDoubleOrNull()
            val interestRate = inputInterest.text.toString().toDoubleOrNull()
            val loanTerm = inputLoan.text.toString().toIntOrNull()

            if (principal != null && interestRate != null && loanTerm != null) {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("PRINCIPAL", principal)
                    putExtra("INTEREST_RATE", interestRate)
                    putExtra("LOAN_TERM", loanTerm)
                }
                startActivityForResult(intent, 1)
            }
        }
    }
           override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
               super.onActivityResult(requestCode, resultCode, data)

               if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
                   val monthlyPayment = data.getStringExtra("MONTHLY_PAYMENT")
                   val totalPayment = data.getStringExtra("TOTAL_PAYMENT")

                   findViewById<TextView>(R.id.monthly_payment_text).text = "Monthly Payment: $$monthlyPayment"
                   findViewById<TextView>(R.id.total_payment_text).text = "Total Payment: $$totalPayment"
               }
           }
    }




