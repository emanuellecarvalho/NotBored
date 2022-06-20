package com.meli.notbored

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import com.meli.notbored.databinding.ActivityMainBinding
import android.widget.CheckBox as CheckBox

class MainActivity : AppCompatActivity(), TextWatcher {

    private lateinit var binding: ActivityMainBinding
    private lateinit var checkBoxTerms: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.participantsNumber.addTextChangedListener(this)
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, ActivityList::class.java)
            startActivity(intent)
1
            Toast.makeText(baseContext, "text", Toast.LENGTH_LONG).show()
        }
        binding.checkboxTermsAndConditions.setOnClickListener() {
            //verifyCheckBox()
        }

        binding.termsAndConditions.setOnClickListener() {
            showTermsConditions()
        }

        val spannable = SpannableString(binding.termsAndConditions.text)
        spannable.setSpan(UnderlineSpan(), 0, spannable.length, 0)
        binding.termsAndConditions.text = spannable

        checkBoxTerms = findViewById(R.id.checkboxTermsAndConditions)

        checkBoxTerms.setOnClickListener() {
            checkBoxFilled()
        }
    }


    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.d("SANTI", "beforeTextChanged")
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //valida dois campos
        if (!p0.isNullOrBlank()) {
            val participantes = binding.participantsNumber.text.toString().toInt()
            if (participantes > 0) binding.btnStart.isEnabled = true
            if (participantes == 0) Toast.makeText(
                baseContext,
                "Participant number cannot be zero!",
                Toast.LENGTH_LONG
            ).show()

        } else {
            binding.btnStart.isEnabled = false
        }

        Log.d("SANTI", "beforeTextChanged")

    }

    override fun afterTextChanged(p0: Editable?) {
        Log.d("SANTI", "afterTextChanged")

    }

    private fun checkBoxFilled() {
        if (checkBoxTerms.isChecked) Toast.makeText(
            applicationContext,
            "Accepted",
            Toast.LENGTH_LONG
        ).show()
        else Toast.makeText(
            applicationContext,
            "Please check this box if you want to proceed.",
            Toast.LENGTH_LONG
        ).show()

    }

    //fragment whit the Terms and Conditions of the app
    private fun showTermsConditions() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, TermConditionFragment())
            .commit()
    }
}