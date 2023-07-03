package com.enterprise.android_app.view.components

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.enterprise.android_app.view_models.ImageViewModel
import io.swagger.client.models.UsersPhotoprofileBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.BufferedSink
import okio.buffer
import okio.source
import java.io.File
import java.io.IOException


@Composable
fun ImageSelectorComponent(
    fileState: MutableState<File?>,
    onFileUploaded: () -> Unit
) {
    val imageViewModel: ImageViewModel = ImageViewModel()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { selectedUri ->
            val mimeType = context.contentResolver.getType(selectedUri)
            if (mimeType == "image/jpeg" || mimeType == "image/png") {
                val contentResolver = context.contentResolver
                val inputStream = contentResolver.openInputStream(selectedUri)
                val fileName = getFileName(contentResolver, selectedUri)
                if (inputStream != null && fileName != null) {
                    val file = File(context.cacheDir, fileName)
                    file.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                    fileState.value = file
                }
            } else {
                // Invalid file type, handle accordingly
            }
        }
    }

    Column {
        Button(
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Text("Select File")
        }

        if (fileState.value != null) {
            Button(
                onClick = {
                    fileState.value?.let { selectedFile ->
                        lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                            try {
                                val requestBody = selectedFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                                val fileBytes = selectedFile.readBytes()
                                val usersPhotoprofileBody = UsersPhotoprofileBody(fileBytes)
                                if (usersPhotoprofileBody.multipartFile != null) {

                                imageViewModel.saveChange(usersPhotoprofileBody)

                                onFileUploaded()
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            ) {
                Text("Upload File")
            }
        }
    }
}

private fun getFileName(contentResolver: ContentResolver, uri: Uri): String? {
    var fileName: String? = null
    val scheme = uri.scheme
    if (scheme == "file") {
        fileName = uri.lastPathSegment
    } else if (scheme == "content") {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (columnIndex != -1) {
                    fileName = it.getString(columnIndex)
                }
            }
            it.close()
        }
    }
    return fileName
}