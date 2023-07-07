package com.enterprise.android_app.view_models

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(): ViewModel() {
    private var userControllerApi: UserControllerApi = UserControllerApi()


    private val coroutineScope = CoroutineScope(Dispatchers.IO)



    fun saveChange(userDTO: UserDTO){
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    if(userDTO.id!=null){
                        userControllerApi.updateUser(userDTO,userDTO.id)


                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            CurrentDataUtils.retrieveCurrentUser()
        }

    }

    fun changePassword(oldPassword: String, newPassword: String){
        coroutineScope.launch {
            try{
                withContext(Dispatchers.IO){
                    if (oldPassword!="" && newPassword!="")
                        userControllerApi.changePassword(oldPassword = oldPassword,newPassword = newPassword)
                }
            } catch (e: java.lang.Exception){
                e.printStackTrace()
            }

        }
    }




}