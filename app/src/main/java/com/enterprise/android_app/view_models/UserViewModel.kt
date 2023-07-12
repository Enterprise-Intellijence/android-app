package com.enterprise.android_app.view_models

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.persistence.AppDatabase
import com.enterprise.android_app.model.persistence.User
import com.enterprise.android_app.navigation.Navigation
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UserViewModel(): ViewModel() {
    private var userControllerApi: UserControllerApi = UserControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    public val updated : MutableState<Boolean> = mutableStateOf(false)
    val localUpdated: MutableState<Boolean> = mutableStateOf(false)

    //val for the DB
    //private val _application = application



    fun saveChange(userDTO: UserDTO) {

        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    userControllerApi.updateUser(userDTO,userDTO.id)

                }
                CurrentDataUtils.retrieveCurrentUser()
                updated.value = true


            } catch (e: Exception) {
                updated.value = false
                e.printStackTrace()
            }
            localUpdated.value = true
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