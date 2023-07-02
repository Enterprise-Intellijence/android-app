package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductPageViewModel(): ViewModel() {

    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private  var _product: MutableState<ProductDTO?> = mutableStateOf(null)
    private lateinit var _isFavourite: MutableState<Boolean>

    var product: ProductDTO? = null
        get() = _product.value

    fun getProductById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _product.value = productControllerApi.productById(id)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}