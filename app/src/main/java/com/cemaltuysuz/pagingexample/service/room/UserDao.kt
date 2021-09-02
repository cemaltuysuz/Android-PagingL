package com.cemaltuysuz.pagingexample.service.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cemaltuysuz.pagingexample.model.UserItem

@Dao
interface UserDao {

    // Delete All
    @Query("DELETE FROM Users")
    suspend fun resetDatabase()

    // Insert All users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFollowers(vararg model:UserItem)


    @Query("SELECT * FROM Users")
    fun getFollowers() : PagingSource<Int,UserItem>
}