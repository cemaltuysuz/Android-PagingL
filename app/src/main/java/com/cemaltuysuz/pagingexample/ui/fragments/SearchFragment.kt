package com.cemaltuysuz.pagingexample.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.databinding.FragmentSearchBinding
import com.cemaltuysuz.pagingexample.repo.UserRepo
import com.cemaltuysuz.pagingexample.utils.Status
import com.cemaltuysuz.pagingexample.viewmodel.UserViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var viewModel:UserViewModel
    private var fragmentSearchBinding : FragmentSearchBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewBinding
        val binding = FragmentSearchBinding.bind(view)
        fragmentSearchBinding = binding

        binding.searchUserInfo.searchedUserContainer.setOnClickListener {
            val username = binding.searchUserInfo.searchListRowUserName.text.toString()
           if(username.isNotEmpty()){
               val action = SearchFragmentDirections.actionSearchFragmentToFollowersFragment(username)
               Navigation.findNavController(it).navigate(action)
               binding.searchWithUsername.setText("")
           }
        }

        // viewModel
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        // SearchListener


        var job :Job? = null
        binding.searchWithUsername.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                it?.let {
                    if (it.isNotEmpty()){
                        delay(1000)
                        viewModel.searchUser(it.toString())
                        progressVisibilityChange(true)
                    }else{
                        progressVisibilityChange(false)
                    }
                }
            }
        }

        observer()
    }

    private fun observer() {
        viewModel.getResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                progressVisibilityChange(false)
                when(it.status){
                    Status.SUCCESS -> viewModel.setUser(it.data!!)
                    Status.ERROR -> Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.getUser.observe(viewLifecycleOwner, Observer {
            try {
                fragmentSearchBinding!!.searchUserInfo.user = it
            }catch (e:Exception){
                Log.d("ERRROR : ",""+e.message)
            }
        })
    }

    private fun progressVisibilityChange(boolean: Boolean){
        if (boolean) fragmentSearchBinding!!.searchLoadProgress.visibility = View.VISIBLE
        else fragmentSearchBinding!!.searchLoadProgress.visibility = View.GONE
    }

}