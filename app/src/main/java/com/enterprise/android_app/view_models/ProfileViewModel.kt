package com.enterprise.android_app.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(visitedUserId: String) : ViewModel() {
    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    val productList = mutableStateListOf<ProductBasicDTO>()
    var currentPage: Int = 0
    var visitedUserId: String = visitedUserId
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val _productList: List<ProductBasicDTO>
        get() = productList

    fun loadNextPage() {
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    productControllerApi.getFilteredProducts(userId = visitedUserId, page = currentPage)
                }
                val productsToAdd = newProducts.content?.toList() ?: emptyList()
                productList.addAll(productsToAdd)
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}