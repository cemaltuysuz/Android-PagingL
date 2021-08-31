package com.cemaltuysuz.pagingexample.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.cemaltuysuz.pagingexample.adapter.FollowersAdapter
import javax.inject.Inject

class FragmentFactory @Inject constructor(
        private val followersAdapter : FollowersAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            FollowersFragment::class.java.name -> FollowersFragment(followersAdapter)
            SearchFragment::class.java.name -> SearchFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}