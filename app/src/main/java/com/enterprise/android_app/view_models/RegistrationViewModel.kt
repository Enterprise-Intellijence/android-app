package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception


class RegistrationViewModel(userControllerApi: UserControllerApi = UserControllerApi()): ViewModel() {

    private val userController: UserControllerApi = userControllerApi

    fun registerUser(username: String, email: String, password: String, showToast: MutableState<Boolean>,errorMessage: MutableState<String>){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = userController.register(username,email,password)
            }
            catch (e: Exception){
                val jsonObject = JSONObject(e.message)
                val resultMap = mutableMapOf<String, String>()

                for (key in jsonObject.keys()) {
                    resultMap[key] = jsonObject.getString(key)
                }
                errorMessage.value = (resultMap["detail"]?:"")
                return@launch
            }
            showToast.value = true
        }
    }


}