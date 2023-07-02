package com.enterprise.android_app.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel : ViewModel(){
    private var userControllerApi: UserControllerApi = UserControllerApi()
    val productList = mutableStateListOf<ProductBasicDTO>()
    var currentPage: Int = 0
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val _productList: List<ProductBasicDTO>
        get() = productList


    fun loadNextPage(){
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    userControllerApi.getLikedProducts(page = currentPage, size = 10)
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