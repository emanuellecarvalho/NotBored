package com.meli.notbored

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.meli.notbored.adapters.AdapterListActivities
import com.meli.notbored.databinding.ActivityListBinding
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.EXTRAS
import com.meli.notbored.domain.ServiceActivities
import com.meli.notbored.util.view.UtilitiesView
import com.meli.notbored.viewmodel.ModelActivityList

class ActivityList : AppCompatActivity() {
    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private var participantsNumber: Int? = null
    private val viewModel: ModelActivityList by viewModels()
    private var activityList: MutableList<Activity>? = null
    private var toolbar: MaterialToolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        UtilitiesView.showToolBar(toolbar!!, baseContext)
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
                onClickItemList(activity)
            }

        }
    }

    private fun onClickItemList(activity: Activity) {
        viewModel.setActivitySelected(activity)
        val intent = Intent(this, ActivitySuggestion::class.java)
        val participants = getIntent().getIntExtra("PARTICIPANT_NUMBER", 0)
        intent.putExtra("PARTICIPANT_NUMBER", participants)
        intent.putExtra(EXTRAS.ATIVIDADE.name, activity)
        startActivity(intent)
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
                val activityRandom: Activity? = activityList?.let {
                    ServiceActivities.getRandomActivity(it)}
                activityRandom?.isRandomic = true
                if (activityRandom != null) {
                    onClickItemList(activityRandom)
                }
                Toast.makeText(baseContext, "${activityRandom?.activity}", Toast.LENGTH_SHORT).show()
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