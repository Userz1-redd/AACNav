package com.example.kotlinhello.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinhello.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var fragmentMusicBinding : FragmentSettingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingBinding.inflate(inflater,container,false)
        fragmentMusicBinding = binding
        return fragmentMusicBinding!!.root
    }

    override fun onDestroyView(){
        fragmentMusicBinding = null
        super.onDestroyView()
    }
}