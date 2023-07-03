package com.enterprise.android_app.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.lang.Thread.sleep

object CurrentDataUtils {
    private var _tokenForRequest: MutableState<String> = mutableStateOf("")
    private var _accessToken: MutableState<String> = mutableStateOf("")
    private var _refreshToken: MutableState<String> = mutableStateOf("")
    private var _currentUser: MutableState<UserDTO?> = mutableStateOf(null)
    private val userControllerApi = UserControllerApi()

    var tokenForRequest: String = ""
        get() = _tokenForRequest.value

    var accessToken: String
        get() = _accessToken.value
        set(newValue) { _accessToken.value = newValue }

    var refreshToken: String
        get() = _refreshToken.value
        set(newValue) { _refreshToken.value = newValue }

    var currentUser: UserDTO? = null
        get() = _currentUser.value

    fun setAccessTokenForRequest() {
        _tokenForRequest.value = _accessToken.value
    }

    fun setRefreshTokenForRequest() {
        _tokenForRequest.value = _refreshToken.value
    }

    fun retrieveCurrentUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                setAccessTokenForRequest()
                _currentUser.value = userControllerApi.me()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}
