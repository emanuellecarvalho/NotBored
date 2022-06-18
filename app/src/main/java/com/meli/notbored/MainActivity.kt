package com.meli.notbored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import com.meli.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.participantsNumber.addTextChangedListener(this)
        binding.btnStart.setOnClickListener {
            Toast.makeText(baseContext, "text", Toast.LENGTH_LONG).show()
        }

        val spannable = SpannableString(binding.termsAndConditions.text)
            spannable.setSpan(UnderlineSpan(),0,  spannable.length, 0)
            binding.termsAndConditions.text = spannable

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.d("SANTI", "beforeTextChanged")
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (!p0.isNullOrBlank()){
            val participantes = binding.participantsNumber.text.toString().toInt()
            if (participantes > 0) binding.btnStart.isEnabled = true
            if (participantes == 0) Toast.makeText(baseContext,
                "Numero de participante nao pode ser zero!",
                Toast.LENGTH_LONG).show()

        }else{
            binding.btnStart.isEnabled = false
        }

        Log.d("SANTI", "beforeTextChanged")

    }

    override fun afterTextChanged(p0: Editable?) {
        Log.d("SANTI", "afterTextChanged")

    }
}