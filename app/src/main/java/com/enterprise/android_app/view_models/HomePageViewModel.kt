package com.enterprise.android_app.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OrderControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.PageProductBasicDTO
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel : ViewModel() {
    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    val productList = mutableStateListOf<ProductBasicDTO>()
    var currentPage: Int = 0
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val _productList: List<ProductBasicDTO>
        get() = productList

    fun loadNextPage() {
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    productControllerApi.getFilteredProducts(page = currentPage)
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

