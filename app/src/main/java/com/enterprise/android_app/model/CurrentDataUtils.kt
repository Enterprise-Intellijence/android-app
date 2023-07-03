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
    private val userControllerApi = UserControllerApi()

    private var _accessToken: MutableState<String> = mutableStateOf("")
    private var _refreshToken: MutableState<String> = mutableStateOf("")
    private var _currentUser: MutableState<UserDTO?> = mutableStateOf(null)
    private var _currentProductId: MutableState<String> = mutableStateOf("")

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

}
