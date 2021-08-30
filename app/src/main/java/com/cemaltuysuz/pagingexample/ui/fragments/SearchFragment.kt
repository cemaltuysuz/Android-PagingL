package com.cemaltuysuz.pagingexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.utils.Status
import com.cemaltuysuz.pagingexample.viewmodel.UserViewModel


class SearchFragment : Fragment() {

    private lateinit var viewModel:UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        observer()
    }

    private fun observer() {
        viewModel.getResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status){
                    Status.SUCCESS -> viewModel.setUser(it.data!!)
                    Status.ERROR -> Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.getUser.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),it.id,Toast.LENGTH_LONG).show()
        })
    }

}