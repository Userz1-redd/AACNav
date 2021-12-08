package com.example.kotlinhello.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinhello.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var fragmentHomeBinding : FragmentHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        fragmentHomeBinding = binding
        return fragmentHomeBinding!!.root
    }

    override fun onDestroyView(){
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}