package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class RegistrationViewModel(userControllerApi: UserControllerApi = UserControllerApi()): ViewModel() {

    private val userController: UserControllerApi = userControllerApi

    fun registerUser(username: String, email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = userController.register(username,email,password)
                println(response)
            }
            catch (e: Exception){
                e.printStackTrace()

        }


        }
    }


}