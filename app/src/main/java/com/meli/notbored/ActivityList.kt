package com.meli.notbored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.meli.notbored.adapters.AdapterListActivities
import com.meli.notbored.databinding.ActivityListBinding
import com.meli.notbored.domain.Activity
import com.meli.notbored.viewmodel.ModelActivityList

class ActivityList : AppCompatActivity() {
    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ModelActivityList by viewModels()

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

        val recycler = binding.lisActivity

        recycler.layoutManager = LinearLayoutManager(baseContext)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.setHasFixedSize(true)

        viewModel.getList().observe(this) { list ->
            recycler.adapter = AdapterListActivities(list) { activity: Activity ->
                onClickItemList(activity)
            }

            Log.d("SANTI", "ESTAMOS DENTRPO")
        }
    }

    private fun onClickItemList(activity: Activity){
        Toast.makeText(baseContext, activity.activity, Toast.LENGTH_LONG).show()
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
                Toast.makeText(baseContext, "OUTRA ACTIVITY AQUI", Toast.LENGTH_LONG).show()
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