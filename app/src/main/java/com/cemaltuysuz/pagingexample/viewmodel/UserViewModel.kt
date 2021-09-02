package com.cemaltuysuz.pagingexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.repo.UserRepo
import com.cemaltuysuz.pagingexample.utils.Resource
import com.cemaltuysuz.pagingexample.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repo: UserRepo) : ViewModel() {

    private val repository = repo

    // -------->  Search Fragment
    private val searchUserResponse = MutableLiveData<Resource<UserItem>>()
    val getSearchResponse : LiveData<Resource<UserItem>> get() = searchUserResponse

    fun searchUser(username: String)  {
        viewModelScope.launch {
            val response = repository.findUser(username) // find user
            searchUserResponse.value = response
        }
    }

    // -------->  Followers Fragment

    private val followersResponse = MutableLiveData<Resource<Status>>()
    val getFollowersResponse :LiveData<Resource<Status>> get() = followersResponse // get user followers

    val getFollowers = repository.getFollowers()

    fun findFollowers(username: String)  {

        viewModelScope.launch {
            val response = repository.findUserFollowers(username)
            followersResponse.value = response
        }
    }

    fun resetDatabase(){
        viewModelScope.launch {
            repository.resetDatabase()
        }
    }

}