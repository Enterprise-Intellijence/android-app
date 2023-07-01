package com.enterprise.android_app.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.PageProductBasicDTO
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Collections.addAll


class HomePageViewModel: ViewModel() {
    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val productList: MutableState<PageProductBasicDTO> = mutableStateOf(PageProductBasicDTO())
    var currentPage: Int = 0

    val _productList : PageProductBasicDTO
        get() = productList.value

    suspend fun loadNextPage() {
        val newProducts = withContext(Dispatchers.IO) {
            productControllerApi.getFilteredProducts(page = currentPage)
        }
        productList.value = newProducts
        currentPage++
    }
}