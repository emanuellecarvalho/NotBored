package com.meli.notbored

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.meli.notbored.databinding.ActivitySuggestionBinding
import com.meli.notbored.domain.Activity
import com.meli.notbored.domain.EXTRAS

class ActivitySuggestion: AppCompatActivity() {
    private var _binding: ActivitySuggestionBinding? = null
    private val binding get() = _binding!!
    private var participantsNumber: Int? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivitySuggestionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //numero de participantes recebidos da outra activity
        participantsNumber = intent.extras?.getInt(EXTRAS.NUMBER_PARTICIPANT.name)
        //Toast.makeText(this, "$participantsNumber", Toast.LENGTH_SHORT).show()

        //aqui ja a atividade pronta para ser populada nas views
        activity = intent.extras?.getParcelable(EXTRAS.ATIVIDADE.name)
        Toast.makeText(this, "${activity?.activity}", Toast.LENGTH_SHORT).show()
    }
}