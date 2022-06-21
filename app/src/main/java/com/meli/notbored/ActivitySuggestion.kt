package com.meli.notbored

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.meli.notbored.databinding.ActivitySuggestionBinding
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.EXTRAS
import com.meli.notbored.domain.ServiceActivities


class ActivitySuggestion : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activity = intent.extras?.getParcelable<Activity>(EXTRAS.ATIVIDADE.name)

        Log.d("SANTI", "${activity?.isRandomic}")

        val numberParticipants = intent.getIntExtra("PARTICIPANT_NUMBER", 0)
//        val categoryActivity = intent.getStringExtra("CATEGORY_TASK")
//        val activityPrice = intent.getStringExtra("PRICE_NAME")
//        val randomCategory = intent.getBooleanExtra("IS_RANDOM", false)

        val toolbar = binding.toolbar
        toolbar.navigationIcon = AppCompatResources.getDrawable(
            baseContext,
            R.drawable.ic_baseline_navigate_before_24
        )
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        binding.numberParticipantsActivitySuggestion.text = numberParticipants.toString()
        binding.textTitleActivitySuggestion.text = activity?.description

        toolbar.title = activity?.activity

        binding.priceParticipantsActivitySuggestion.text =
            activity?.price?.let { ServiceActivities.priceCategory(it) }

        if (activity?.isRandomic == true) {
            binding.toolbar.title = "Random"
            binding.randomCategoryActivitySuggestion.isVisible = true
            binding.randomCategoryActivitySuggestion.text = activity.activity
        }


        binding.btnTryAnother.setOnClickListener(View.OnClickListener {
            val activity1 = ServiceActivities.getRandomActivity(ServiceActivities.getList())
            toolbar.title = activity1.activity
            binding.priceParticipantsActivitySuggestion.text =
                activity1.price.let { ServiceActivities.priceCategory(it) }
            binding.textTitleActivitySuggestion.text = activity1.description

        })
    }

    //get options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

