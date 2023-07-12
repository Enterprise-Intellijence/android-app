package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ReportControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ReportDTO
import io.swagger.client.models.UserBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReportedViewModel(): ViewModel() {

    val ReportControllerApi: ReportControllerApi = ReportControllerApi()
    val reportedUser: MutableState<UserBasicDTO?> = mutableStateOf(null)
    val userControllerApi: UserControllerApi = UserControllerApi()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun report(report: ReportDTO) {
        coroutineScope.launch {
            try {
                val newReport = withContext(Dispatchers.IO) {
                    ReportControllerApi.createReport(report)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadReportedUser(userId: String) {
        coroutineScope.launch {
            try {
                val newReportedUser = withContext(Dispatchers.IO) {
                    userControllerApi.userById(userId)
                }
                reportedUser.value = newReportedUser
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}