package com.cemaltuysuz.pagingexample.repo

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import com.cemaltuysuz.pagingexample.service.room.UserDao
import com.cemaltuysuz.pagingexample.utils.Resource
import com.cemaltuysuz.pagingexample.utils.Status
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(api:Api,dao:UserDao) : UserRepoInterface  {

    private var request = api
    private val userDao = dao


    override suspend fun findUser(username: String): Resource<UserItem> {
        return try {
            val searchUserResponse : Response<UserItem> = request.findUser(username)

            if (searchUserResponse.isSuccessful){
                searchUserResponse.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            }else{
                Resource.error("No data !",null)
            }
        }catch (e:Exception){
            Resource.error(e.message!!,null)
        }
    }

    override suspend fun findUserFollowers(username: String): Resource<Status> {
        return  try {
            val findFollowersResponse : Response<List<UserItem>> = request.findUserFollowers(username)

            if (findFollowersResponse.isSuccessful){
                findFollowersResponse.body()?.let {
                    userDao.insertAllFollowers(*it.toTypedArray())
                    return@let Resource.success(null)
                } ?: Resource.error("Error",null)
            }else{
                Resource.error("No data !",null)
            }
        }catch (e:Exception){
            Resource.error(e.message!!,null)
        }
    }

    override suspend fun resetDatabase() {
        userDao.resetDatabase()
    }

    override  fun getFollowers(): PagingSource<Int,UserItem> {
        return userDao.getFollowers()
    }



}