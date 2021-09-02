package com.cemaltuysuz.pagingexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.repo.UserRepo
import com.cemaltuysuz.pagingexample.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repo: UserRepo) : ViewModel() {

    private val repository = repo

    // -------->  Search Fragment

    private val searchUserResponse = MutableLiveData<Resource<UserItem>>()
    val getSearchResponse : LiveData<Resource<UserItem>> get() = searchUserResponse

    // Search user
    fun searchUser(username: String)  {
        viewModelScope.launch {
            val response = repository.findUser(username)
            searchUserResponse.value = response
        }
    }


    // -------->  Followers Fragment

    private val followers = MutableLiveData<List<UserItem>>()
    val getFollowers :LiveData<List<UserItem>> get() = followers // get user
    fun setUser(value: List<UserItem>) { followers.value = value } // Set user


    //fun findFollowers(username:String) {repository.findFollowers(username)} // find user's followers
}