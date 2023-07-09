package com.enterprise.android_app.view_models

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.BasePath
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.infrastructure.ApiClient
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductPageViewModel: ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private var _product: MutableState<ProductDTO?> = mutableStateOf(null)
    val sellerProducts = mutableStateListOf<ProductBasicDTO>()
    val relatedProducts = mutableStateListOf<ProductBasicDTO>()


    var currentSellerProductPage: Int = 0
    var currentRelatedProductPage: Int = 0

    var product: ProductDTO? = null
        get() = _product.value

    val _sellerProducts: List<ProductBasicDTO>
        get() = sellerProducts

    val _relatedProducts: List<ProductBasicDTO>
        get() = relatedProducts

    fun getProductById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _product.value = productControllerApi.productById(id)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun loadNextSellerProductPage() {
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    productControllerApi.getFilteredProducts(userId = _product.value?.seller?.id, page = currentSellerProductPage, sizePage = 20)
                }
                val productsToAdd = newProducts.content?.toList() ?: emptyList()
                sellerProducts.addAll(productsToAdd)
                currentSellerProductPage++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadNextRelatedProductPage() {
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    productControllerApi.getFilteredProducts(
                        primaryCat = _product.value?.productCategory?.primaryCat,
                        secondaryCat = _product.value?.productCategory?.secondaryCat,
                        tertiaryCat = _product.value?.productCategory?.tertiaryCat,
                        page = currentRelatedProductPage)
                }
                val productsToAdd = newProducts.content?.toList() ?: emptyList()
                relatedProducts.addAll(productsToAdd)
                currentRelatedProductPage++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    public fun getProductShareLink(product: ProductDTO): String {
        CurrentDataUtils.currentUser?.let {
            if(product.visibility == ProductDTO.Visibility.PRIVATE) {
                // todo get capability to share private products
                val token = productControllerApi.createCapability(product.id!!).capabilityToken
                return "${BasePath.WEB_BASE_PATH}/products/token/$token"
            }
            if(it.id == product.seller?.id) {
                return "${BasePath.WEB_BASE_PATH}/products/${product.id}"
            }
        }
        return "${BasePath.WEB_BASE_PATH}/products/${product.id}"
    }

}

