package com.cemaltuysuz.pagingexample.repo

import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import com.cemaltuysuz.pagingexample.service.room.UserDao
import com.cemaltuysuz.pagingexample.utils.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(api:Api,dao:UserDao) : UserRepoInterface  {

    private var request = api
    private val userDao = dao
    private val disposable = CompositeDisposable()


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

    override fun findUserFollowers(username: String): Resource<List<UserItem>> {
        return  try {
            val findFollowersResponse : Response<List<UserItem>> = request.findUserFollowers(username)

            if (findFollowersResponse.isSuccessful){
                findFollowersResponse.body()?.let {
                    return@let Resource.success(it)
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

    override suspend fun insertAllFollowers(users: List<UserItem>) {
        userDao.insertAllFollowers(*users.toTypedArray())
    }

    override suspend fun getFollowers(): List<UserItem> {
        return userDao.getFollowers()
    }



}