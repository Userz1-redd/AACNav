package com.example.kotlinhello.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinhello.Home.SearchView.SearchroomActivity
import com.example.kotlinhello.databinding.FragmentRoomBinding

class RoomFragment : Fragment(){
    private var fragmentRoomBinding : FragmentRoomBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRoomBinding.inflate(inflater,container,false)
        fragmentRoomBinding = binding
        fragmentRoomBinding!!.searchRoomBtn.setOnClickListener{
            var intent = Intent(activity, SearchroomActivity::class.java)
            startActivity(intent)
        }
        return fragmentRoomBinding!!.root
    }

    override fun onDestroyView(){
        fragmentRoomBinding = null
        super.onDestroyView()
    }
}