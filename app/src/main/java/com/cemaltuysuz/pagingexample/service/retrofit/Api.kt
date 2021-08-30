package com.cemaltuysuz.pagingexample.service.retrofit

import com.cemaltuysuz.pagingexample.model.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    // Sample
    //https://api.github.com/users/JakeWharton/followers

    // User profile
    @GET("/users/{username}")
        fun findUser(
        @Path("username") username : String,
    ) : Single<User>

        // user followers
    @GET("/users/{username}/Followers")
        fun findUserFollowers(
        @Path("username") username : String,
    ) : Single<User>
}