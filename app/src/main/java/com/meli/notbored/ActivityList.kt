package com.meli.notbored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.meli.notbored.adapters.AdapterListActivities
import com.meli.notbored.databinding.ActivityListBinding
import com.meli.notbored.domain.Activity

class ActivityList : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        toolbar.navigationIcon = AppCompatResources.getDrawable(
            baseContext,
            R.drawable.ic_baseline_navigate_before_24
        )
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recycler = binding.lisActivity
        val list= mutableListOf<Activity>()
        for (activity in resources.getStringArray(R.array.activity_list)){
            list.add(Activity(activity))
        }

        recycler.layoutManager = LinearLayoutManager(baseContext)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.setHasFixedSize(true)

        recycler.adapter = AdapterListActivities(list) { activity: Activity ->
            Toast.makeText(baseContext, "$activity", Toast.LENGTH_LONG).show()
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
                //newGame()
                true
            }
            androidx.appcompat.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}