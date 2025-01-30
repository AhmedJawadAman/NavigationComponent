package com.example.navigationcomponent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.FragmentSecondFragmentBinding

class SecondFragment : Fragment() {
    private lateinit var _binding: FragmentSecondFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSecondFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.GoToFirst.setOnClickListener{

            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
        _binding.GoToThird.setOnClickListener{

            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
    }

}