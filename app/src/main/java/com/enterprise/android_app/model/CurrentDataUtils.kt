package com.enterprise.android_app.model

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.enterprise.android_app.model.persistence.AppDatabase
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.User
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CurrentDataUtils {
    private val userControllerApi = UserControllerApi()

    private var _accessToken: MutableState<String> = mutableStateOf("")
    private var _refreshToken: MutableState<String> = mutableStateOf("")
    private var _currentUser: MutableState<UserDTO?> = mutableStateOf(null)
    private var _currentProductId: MutableState<String> = mutableStateOf("")
    private var _visitedUser: MutableState<UserBasicDTO?> = mutableStateOf(null)
    var _application: Application? = null

    var accessToken: String
        get() = _accessToken.value
        set(newValue) { _accessToken.value = newValue }

    var refreshToken: String
        get() = _refreshToken.value
        set(newValue) { _refreshToken.value = newValue }

    var currentUser: UserDTO? = null
        get() = _currentUser.value

    fun retrieveCurrentUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _currentUser.value = userControllerApi.me()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    var currentProductId: String
        get() = _currentProductId.value
        set(newValue) { _currentProductId.value = newValue }

    var visitedUser: UserBasicDTO
        get() = _visitedUser.value!!
        set(newValue) { _visitedUser.value = newValue }

    fun toUserBasicDTO(u: UserDTO): UserBasicDTO {
        return UserBasicDTO(
            id = u.id,
            username = u.username,
            bio = u.bio,
            photoProfile = u.photoProfile,
            reviewsTotalSum = u.reviewsTotalSum,
            reviewsNumber = u.reviewsNumber,
            followersNumber = u.followersNumber,
            followingNumber = u.followingNumber
        )
    }

    fun setRefresh(refresh_token: String){
        _refreshToken.value = refresh_token
        CoroutineScope(Dispatchers.IO).launch{
            var user = com.enterprise.android_app.model.persistence.User(null, refresh_token)
            var refresh_token2 = AppDatabase.getInstance(_application?.applicationContext!!).userDao().getRefreshToken()
            println(refresh_token2)
            if( refresh_token2 == null){
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().insert(user)
                println("INSERT")
            }
            else{
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().update(user)
                println("UPDATE")
            }

        }
    }

}
