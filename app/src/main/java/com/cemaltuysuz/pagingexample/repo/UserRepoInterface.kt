package com.cemaltuysuz.pagingexample.repo

import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.utils.Resource


interface UserRepoInterface {

    // Api requests

    suspend fun findUser(username : String, ) : Resource<UserItem>

    fun findUserFollowers(username : String, ) : Resource<List<UserItem>>

    // DAO Requests

    suspend fun resetDatabase()

    suspend fun insertAllFollowers(users : List<UserItem>)

    suspend fun getFollowers() : List<UserItem>
}