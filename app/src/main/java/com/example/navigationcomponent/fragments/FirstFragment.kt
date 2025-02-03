package com.example.navigationcomponent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.FragmentFirstFragmentBinding

class FirstFragment : Fragment() {
   private lateinit var _binding: FragmentFirstFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentFirstFragmentBinding.inflate(inflater , container, false)
        return _binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.GoToSecond.setOnClickListener{

            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        _binding.GoToThird.setOnClickListener{

            findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
        }
        loadNestedFragment()
    }
    private fun loadNestedFragment() {
        // Use childFragmentManager to load the nested fragment
        childFragmentManager.commit {
            replace(R.id.nestedFragmentContainer, NestedFirstFragment())
            setReorderingAllowed(true)
        }
    }
}