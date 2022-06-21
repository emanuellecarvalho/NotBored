package com.meli.notbored

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.meli.notbored.databinding.ActivitySuggestionBinding


class ActivitySuggestion : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberParticipants = intent.getIntExtra("PARTICIPANT_NUMBER", 0)
        val categoryActivity = intent.getStringExtra("CATEGORY_TASK")
        val activityPrice = intent.getStringExtra("PRICE_NAME")
        val randomCategory = intent.getBooleanExtra("IS_RANDOM", false)

        binding.numberParticipantsActivitySuggestion.text = numberParticipants.toString()

        binding.toolbar.title = categoryActivity

        binding.priceParticipantsActivitySuggestion.text = activityPrice

        if (randomCategory) {
            binding.toolbar.title = "Random"
            binding.randomCategoryActivitySuggestion.isVisible = true
            binding.randomCategoryActivitySuggestion.text = categoryActivity
        }

    }


}

