package com.enterprise.android_app.view_models

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.UserServices
import com.enterprise.android_app.model.persistence.AppDatabase

import com.enterprise.android_app.model.persistence.User
import com.enterprise.android_app.model.persistence.UserDao
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(userControllerApi: UserControllerApi = UserControllerApi()): ViewModel() {
    private val userController: UserControllerApi = userControllerApi


    fun authenticate(username: String, password: String, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val tokenMap = userController.authenticate(username, password)
                if (tokenMap.isNotEmpty()) {
                    CurrentDataUtils.accessToken = tokenMap["accessToken"].toString()
                    CurrentDataUtils.setRefresh(tokenMap["refreshToken"].toString())
                    CurrentDataUtils.retrieveCurrentUser()

                    UserServices.retriveLikedProducts()
                    AppRouter.navigateTo(Screen.MainScreen)
                } else {
                    onError()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun authenticateGoogle(idToken: String, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tokenMap = userController.googleAuth(idToken)
                if (tokenMap.isNotEmpty()) {
                    CurrentDataUtils.accessToken = tokenMap["accessToken"].toString()
                    CurrentDataUtils.setRefresh(tokenMap["refreshToken"].toString())
                    CurrentDataUtils.retrieveCurrentUser()

                    UserServices.retriveLikedProducts()
                    AppRouter.navigateTo(Screen.MainScreen)
                } else {
                    onError()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

}
