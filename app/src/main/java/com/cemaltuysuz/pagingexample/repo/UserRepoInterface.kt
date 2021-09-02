package com.cemaltuysuz.pagingexample.repo

import androidx.lifecycle.LiveData
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.utils.Resource
import com.cemaltuysuz.pagingexample.utils.Status


interface UserRepoInterface {

    // Api requests

    suspend fun findUser(username : String, ) : Resource<UserItem>

    suspend fun findUserFollowers(username : String, ) : Resource<Status>

    // DAO Requests

    suspend fun resetDatabase()

    fun getFollowers() : LiveData<List<UserItem>>
}