package com.enterprise.android_app.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.swagger.client.models.UserDTO

object CurrentDataUtils {
    private var _accessToken: MutableState<String> = mutableStateOf("")
    private var _refreshToken: MutableState<String> = mutableStateOf("")
    private lateinit var _currentUser: MutableState<UserDTO>

    var accessToken: String
        get() = _accessToken.value
        set(newValue) { _accessToken.value = newValue }

    var refreshToken: String
        get() = _refreshToken.value
        set(newValue) { _refreshToken.value = newValue }

    var currentUser: UserDTO
        get() = _currentUser.value
        set(newValue) { _currentUser.value = newValue }

}
