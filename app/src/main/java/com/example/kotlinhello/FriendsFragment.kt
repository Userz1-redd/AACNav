package com.example.kotlinhello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinhello.DataClass.MyFriend
import com.example.kotlinhello.databinding.FragmentFriendsBinding

class FriendsFragment : Fragment() {
    private var mBinding : FragmentFriendsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var friendsList = ArrayList<MyFriend>()
        for(i in 1..10){
            var testFriend = MyFriend(name = "친구${i}", phonenumber = "000-0000-0000")
            friendsList.add(testFriend)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFriendsBinding.inflate(inflater,container,false)
        mBinding = binding
        return mBinding!!.root
    }

    override fun onDestroyView(){
        mBinding = null
        super.onDestroyView()
    }
}