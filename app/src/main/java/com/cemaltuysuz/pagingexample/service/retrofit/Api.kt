package com.cemaltuysuz.pagingexample.service.retrofit

import com.bumptech.glide.load.engine.Resource
import com.cemaltuysuz.pagingexample.model.UserItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    // Sample
    //https://api.github.com/users/JakeWharton/followers

    // User profile
    @GET("/users/{username}")
       suspend fun findUser(
        @Path("username") username : String,
    ) : Response<UserItem>

        // user followers
    @GET("/users/{username}/followers")
        suspend fun findUserFollowers(
        @Path("username") username : String,
    ) : Response<List<UserItem>>
}