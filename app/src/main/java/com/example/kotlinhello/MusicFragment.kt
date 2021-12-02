package com.example.kotlinhello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinhello.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {
    private var fragmentMusicBinding : FragmentMusicBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMusicBinding.inflate(inflater,container,false)
        fragmentMusicBinding = binding
        return fragmentMusicBinding!!.root
    }

    override fun onDestroyView(){
        fragmentMusicBinding = null
        super.onDestroyView()
    }
}