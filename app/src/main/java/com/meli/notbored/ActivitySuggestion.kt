package com.meli.notbored

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meli.notbored.databinding.ActivitySuggestionBinding

class ActivitySuggestion: AppCompatActivity() {
    private var _binding: ActivitySuggestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivitySuggestionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}