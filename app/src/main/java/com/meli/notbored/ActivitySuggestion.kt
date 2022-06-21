package com.meli.notbored

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.meli.notbored.databinding.ActivitySuggestionBinding


class ActivitySuggestion : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberParticipants = getIntent().getIntExtra("PARTICIPANT_NUMBER", 0)
        val categoryActivity = getIntent().getStringExtra("CATEGORY_TASK")
        val activityPrice = getIntent().getStringExtra("PRICE_NAME")
        val randomCategory = getIntent().getBooleanExtra("IS_RANDOM", false)

        binding.numberParticipantsActivitySuggestion.text = numberParticipants.toString()

        binding.toolbar.title = categoryActivity

        binding.priceParticipantsActivitySuggestion.text = activityPrice

        if (randomCategory == true) {
            binding.toolbar.title = "Random"
            binding.randomCategoryActivitySuggestion.text = categoryActivity
        }
    }


}

