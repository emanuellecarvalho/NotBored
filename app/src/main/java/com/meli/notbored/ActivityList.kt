package com.meli.notbored

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.meli.notbored.adapters.AdapterListActivities
import com.meli.notbored.databinding.ActivityListBinding
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.EXTRAS
import com.meli.notbored.domain.ServiceActivities
import com.meli.notbored.viewmodel.ModelActivityList

class ActivityList : AppCompatActivity() {
    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private var participantsNumber: Int? = null
    private val viewModel: ModelActivityList by viewModels()
    private var activityList: MutableList<Activity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        toolbar.navigationIcon = AppCompatResources.getDrawable(
            baseContext,
            R.drawable.ic_baseline_navigate_before_24
        )
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        participantsNumber = intent.extras?.run {
            getInt(EXTRAS.NUMBER_PARTICIPANT.name)
        }

        Toast.makeText(this, "$participantsNumber", Toast.LENGTH_SHORT).show()

        val recycler = binding.listActivity

        recycler.layoutManager = LinearLayoutManager(baseContext)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.setHasFixedSize(true)

        viewModel.getList().observe(this) { list ->
            activityList = list
            recycler.adapter = AdapterListActivities(list) { activity: Activity ->
                startActivitySuggestion(activity, participantsNumber)
            }
        }
    }

    private fun startActivitySuggestion(activity: Activity, participantsNumber:Int?) {
        if (participantsNumber != null) {
            val intent = Intent(this@ActivityList, ActivitySuggestion::class.java)
                intent.putExtra(EXTRAS.NUMBER_PARTICIPANT.name, participantsNumber)
                intent.putExtra(EXTRAS.ATIVIDADE.name, activity)

            startActivity(intent)
        }
    }

    //inflate the menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    //get options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.random -> {
                val activity = activityList?.let { ServiceActivities.getRandomActivity(it) }
                activity?.let { startActivitySuggestion(it, participantsNumber) }
                Toast.makeText(baseContext, "${activity?.activity}", Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}