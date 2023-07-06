package com.enterprise.android_app.model.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Query("SELECT refresh_token FROM user")
    fun getRefreshToken(): String

    @Insert
    fun insert(vararg users: User)

    @Delete
    fun delete(user: User)
}