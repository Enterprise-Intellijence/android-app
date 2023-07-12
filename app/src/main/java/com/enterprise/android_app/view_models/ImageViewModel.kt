package com.enterprise.android_app.view_models

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.BasePath
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.ImageControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ProductDTO
import io.swagger.client.models.UserDTO
import io.swagger.client.models.UsersPhotoprofileBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File
import java.io.InputStream

class ImageViewModel: ViewModel() {

    private val client = OkHttpClient()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun updateUserImage(uri: Uri?, img: InputStream): Boolean {
        var updated = true

        try {
            var file: File? = null
            if (uri != null)
                file = File(uri.path!!)

            println("id: " + CurrentDataUtils.currentUser)

            val urlBuilder =
                (BasePath.BASE_PATH + "/api/v1/images/users/photo-profile/" + CurrentDataUtils.currentUser?.photoProfile!!.id).toHttpUrlOrNull()
                    ?.newBuilder()
            val url = urlBuilder!!.build()

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "multipartFile",
                    file?.name,
                    RequestBody.create("image/*".toMediaTypeOrNull(), img.readAllBytes())
                )
                .build()

            val request = Request.Builder()
                .url(url)
                .put(requestBody)
                .addHeader("Authorization", CurrentDataUtils.accessToken)
                .build()

            coroutineScope.launch {
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }
                println("response: " + response)
                updated = response.isSuccessful

                CurrentDataUtils.retrieveCurrentUser()
            }

        }
        catch (e: Exception) {
            updated = false
            e.printStackTrace()
        }
        return updated
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun saveImage(uri: Uri?, img: InputStream): Boolean {
        var saved = true

        try {
            var file: File? = null
            if (uri != null)
                file = File(uri.path!!)

            println("id: " + CurrentDataUtils.currentUser)

            val urlBuilder =
                (BasePath.BASE_PATH + "/api/v1/images/users/photo-profile").toHttpUrlOrNull()
                    ?.newBuilder()
            val url = urlBuilder!!.build()

            urlBuilder.addQueryParameter("description", "user image")

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "multipartFile",
                    file?.name,
                    RequestBody.create("image/*".toMediaTypeOrNull(), img.readAllBytes())
                )
                .build()

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Authorization", CurrentDataUtils.accessToken)
                .build()

            coroutineScope.launch {
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }
                println("response: " + response)
                saved = response.isSuccessful

                CurrentDataUtils.retrieveCurrentUser()
            }

        }
        catch (e: Exception) {
            saved = false
            e.printStackTrace()
        }
        return saved
    }
}