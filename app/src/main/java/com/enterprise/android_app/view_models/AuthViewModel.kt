package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel(userControllerApi: UserControllerApi = UserControllerApi()): ViewModel() {

    private val userController: UserControllerApi = userControllerApi


    fun authenticate(username: String, password: String, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val tokenMap = userController.authenticate(username, password)
                if (tokenMap.isNotEmpty()) {
                    CurrentDataUtils.accessToken = tokenMap["accessToken"].toString()
                    CurrentDataUtils.refreshToken = tokenMap["refreshToken"].toString()
                    //CurrentDataUtils.currentUser = userController.me()
                    AppRouter.navigateTo(Screen.MainScreen)
                } else {
                    onError()
                }
            } catch (e: Exception) {
                onError()
            }


        }
    }


}
