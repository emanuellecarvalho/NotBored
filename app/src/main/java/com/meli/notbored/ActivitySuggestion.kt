package com.meli.notbored

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.appbar.MaterialToolbar
import com.meli.notbored.databinding.ActivitySuggestionBinding
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.EXTRAS
import com.meli.notbored.domain.ServiceActivities
import com.meli.notbored.util.view.UtilitiesView
import com.meli.notbored.viewmodel.ModelActivityList


class ActivitySuggestion : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private var toolbar: MaterialToolbar? = null
    private val viewModel: ModelActivityList by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        UtilitiesView.showToolBar(toolbar!!, baseContext)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val activity = intent.extras?.getParcelable<Activity>(EXTRAS.ATIVIDADE.name)
        val numberParticipants = intent.getIntExtra("PARTICIPANT_NUMBER", 0)
        binding.numberParticipantsActivitySuggestion.text = numberParticipants.toString()

        if (activity != null) {
            viewModel.setActivitySelected(activity)
        }

        binding.btnTryAnother.setOnClickListener {
            val activity1 = ServiceActivities.getRandomActivity(ServiceActivities.getList())
            viewModel.setActivitySelected(activity1)
        }

        viewModel.selectedActivity().observe(this) {
            if (it != null) {
                updateView(it)
            }
        }
    }

    private fun updateView(activity: Activity){
        binding.textTitleActivitySuggestion.text = activity.description
        toolbar?.title = activity.activity
        binding.priceParticipantsActivitySuggestion.text =
            activity.price.let { ServiceActivities.priceCategory(it) }

        if (activity.isRandomic) {
            binding.toolbar.title = "Random"
            binding.randomCategoryActivitySuggestion.isVisible = true
            binding.randomCategoryActivitySuggestion.text = activity.activity
        }
    }

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

    override fun onDestroy() {
        super.onDestroy()
    }
}

