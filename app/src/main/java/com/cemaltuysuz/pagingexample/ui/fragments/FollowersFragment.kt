package com.cemaltuysuz.pagingexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cemaltuysuz.pagingexample.R

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val username = FollowersFragmentArgs.fromBundle(it).username
            Toast.makeText(requireContext(),username,Toast.LENGTH_LONG).show()
        }

    }

}