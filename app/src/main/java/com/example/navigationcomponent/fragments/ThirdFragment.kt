package com.example.navigationcomponent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.FragmentThirdFragmentBinding

class ThirdFragment : Fragment() {
    private lateinit var _binding:FragmentThirdFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentThirdFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.GoToFirst.setOnClickListener{

            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
        }
        _binding.GoToSecond.setOnClickListener{

            findNavController().navigate(R.id.action_thirdFragment_to_secondFragment)
        }
    }
}