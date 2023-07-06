package com.enterprise.android_app.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.apis.ReviewControllerApi
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ReviewDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(var visitedUserId: String) : ViewModel() {
    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val reviewControllerApi: ReviewControllerApi = ReviewControllerApi()
    val productList = mutableStateListOf<ProductBasicDTO>()
    val reviewList = mutableStateListOf<ReviewDTO>()
    var currentProductPage: Int = 0
    var currentReviewPage: Int = 0
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val _productList: List<ProductBasicDTO>
        get() = productList

    val _reviewList: List<ReviewDTO>
        get() = reviewList

    fun loadNextProductPage() {
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    productControllerApi.getFilteredProducts(userId = visitedUserId, page = currentProductPage)
                }
                val productsToAdd = newProducts.content?.toList() ?: emptyList()
                productList.addAll(productsToAdd)
                currentProductPage++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadNextReviewPage() {
        coroutineScope.launch {
            try {
                val newReviews = withContext(Dispatchers.IO) {
                    reviewControllerApi.allReviewReceived(userId = visitedUserId, page = currentProductPage)
                }
                println(newReviews)
                val reviewsToAdd = newReviews.content?.toList() ?: emptyList()
                reviewList.addAll(reviewsToAdd)
                currentReviewPage++

                if (newReviews.last == true) {
                    coroutineScope.cancel()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}