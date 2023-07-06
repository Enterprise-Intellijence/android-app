package com.enterprise.android_app.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,

    @ColumnInfo(name = "refresh_token") val refreshToken: String?
)