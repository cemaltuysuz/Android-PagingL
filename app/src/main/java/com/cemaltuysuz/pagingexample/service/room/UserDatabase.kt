package com.cemaltuysuz.pagingexample.service.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cemaltuysuz.pagingexample.model.UserItem

@Database(entities = [UserItem::class],version = 1,exportSchema = false)
abstract class UserDatabase () : RoomDatabase() {
    abstract fun userDao (): UserDao
}