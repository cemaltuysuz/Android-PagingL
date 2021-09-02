package com.cemaltuysuz.pagingexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.adapter.FollowersAdapter
import com.cemaltuysuz.pagingexample.databinding.FragmentFollowersBinding
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.utils.Status
import com.cemaltuysuz.pagingexample.viewmodel.UserViewModel
import javax.inject.Inject

class FollowersFragment @Inject constructor(adapter:FollowersAdapter) : Fragment(R.layout.fragment_followers) {

    private lateinit var viewModel : UserViewModel
    private val adapter = adapter
    private var fragmentFollowersBinding : FragmentFollowersBinding? = null
    private var username:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewBinding
        val binding = FragmentFollowersBinding.bind(view)
        fragmentFollowersBinding = binding

        // RecyclerView settings
        binding.userFollowersList.layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.userFollowersList.setHasFixedSize(true)
        binding.userFollowersList.adapter = adapter

        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java) // ViewModel init

        observe() // Observer start

        arguments?.let {
            val username = FollowersFragmentArgs.fromBundle(it).username
            if (username.isNotEmpty()) {
                viewModel.findFollowers(username)
                this.username = username
            }
        }
    }

    private fun observe() {
        // Followers API response
        viewModel.getFollowersResponse.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> Toast.makeText(requireContext(),"Success", Toast.LENGTH_SHORT).show()
                Status.ERROR   -> Toast.makeText(requireContext(),"Error : ${it.message}", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(requireContext(),"Something went wrong !", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getFollowers.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) adapter.onDataChange(it as ArrayList<UserItem>)

            Toast.makeText(requireContext(),"databaseden geldi ${it.size}",Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        deleteDatabase()
        super.onDestroy()
    }

    override fun onResume() {
        username?.let {
            if (it.isNotEmpty()) viewModel.findFollowers(it)
        }
        super.onResume()
    }

    override fun onPause() {
        deleteDatabase()
        super.onPause()
    }

    private fun deleteDatabase(){
        viewModel.resetDatabase()
    }
}