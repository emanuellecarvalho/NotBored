package com.meli.notbored

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meli.notbored.databinding.TermConditionBinding

class TermConditionFragment : Fragment() {

    lateinit var binding: TermConditionBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = TermConditionBinding.inflate(inflater)
        binding.iconCloseFragment.setOnClickListener{
            closeFragment()
        }
        return binding.root
    }

    private fun closeFragment() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

}
