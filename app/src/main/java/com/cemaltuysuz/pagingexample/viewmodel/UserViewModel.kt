package com.cemaltuysuz.pagingexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.cemaltuysuz.pagingexample.model.User
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repo: UserRepo) : ViewModel() {

    private val repository = repo

    // -------->  Search Fragment

    private val user = MutableLiveData<UserItem>() // user
    val getUser :LiveData<UserItem> get() = user // get user
    fun setUser(value: UserItem) { user.value = value } // Set user

    // response LiveData
    val getResponse = repository.getSearchedUser // response

    // Search user
    fun searchUser(username: String) = repository.searchUser(username) // find user


    // -------->  Followers Fragment

    private val followers = MutableLiveData<List<UserItem>>()
    val getFollowers :LiveData<List<UserItem>> get() = followers // get user
    fun setUser(value: List<UserItem>) { followers.value = value } // Set user

    val responseFollowers = repository.getUserFollowers

    fun findFollowers(username:String) {repository.findFollowers(username)} // find user's followers
}