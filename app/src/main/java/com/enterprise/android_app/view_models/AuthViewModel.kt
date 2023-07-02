package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(userControllerApi: UserControllerApi = UserControllerApi()): ViewModel() {

    private val userController: UserControllerApi = userControllerApi


    fun authenticate(username: String, password: String) {
        /*
        CoroutineScope(Dispatchers.IO).launch {
            val tokenMap = userController.authenticate(username, password)
            CurrentDataUtils.accessToken = tokenMap["accessToken"].toString()
            CurrentDataUtils.refreshToken = tokenMap["refreshToken"].toString()

            //CurrentDataUtils.currentUser = userController.me()
        }*/
    }


}
