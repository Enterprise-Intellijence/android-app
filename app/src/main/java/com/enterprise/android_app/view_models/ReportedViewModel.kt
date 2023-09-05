package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.apis.ReportControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ProductDTO
import io.swagger.client.models.ReportDTO
import io.swagger.client.models.UserBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReportedViewModel(): ViewModel() {

    val ReportControllerApi: ReportControllerApi = ReportControllerApi()
    val userControllerApi: UserControllerApi = UserControllerApi()
    val productControllerApi: ProductControllerApi = ProductControllerApi()
    private var _reportedUser: MutableState<UserBasicDTO?> = mutableStateOf(null)
    private var _reportedProduct: MutableState<ProductDTO?> = mutableStateOf(null)

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val reportedProduct: ProductDTO?
        get() = _reportedProduct.value

    val reportedUser: UserBasicDTO?
        get() = _reportedUser.value

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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _reportedUser.value = userControllerApi.userById(userId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getProductById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _reportedProduct.value = productControllerApi.productById(id)
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}