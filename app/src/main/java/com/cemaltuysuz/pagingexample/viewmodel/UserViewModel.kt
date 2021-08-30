package com.cemaltuysuz.pagingexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repo:UserRepo) : ViewModel() {

    private val repository = repo


    private val user = MutableLiveData<UserItem>()

    val getUser :LiveData<UserItem>
    get() = user

    fun setUser(value:UserItem) {
        user.value = value
    }

    // response LiveData
    val getResponse = repository.getSearchedUser

    // Search user
    fun searchUser(username:String) = repository.searchUser(username)
}