package com.enterprise.android_app.view_models

import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.ImageControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.UserDTO
import io.swagger.client.models.UsersPhotoprofileBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class ImageViewModel {
    private var imageControllerApi: ImageControllerApi = ImageControllerApi()


    private val coroutineScope = CoroutineScope(Dispatchers.IO)



    fun saveChange(image: UsersPhotoprofileBody){

        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    if(CurrentDataUtils.currentUser?.id != null){

                        imageControllerApi.savePhotoUser(image,"")
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            CurrentDataUtils.retrieveCurrentUser()
        }

    }
}