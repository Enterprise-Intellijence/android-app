package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.FollowingControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.apis.ReportControllerApi
import io.swagger.client.apis.ReviewControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ReportDTO
import io.swagger.client.models.ReviewDTO
import io.swagger.client.models.UserBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel() : ViewModel() {
    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val reviewControllerApi: ReviewControllerApi = ReviewControllerApi()
    private val followingControllerApi: FollowingControllerApi = FollowingControllerApi()
    private val userControllerApi: UserControllerApi = UserControllerApi()
    private val reportControllerApi: ReportControllerApi = ReportControllerApi()

    val productList = mutableStateListOf<ProductBasicDTO>()
    val reviewList = mutableStateListOf<ReviewDTO>()

    val areProducts: MutableState<Boolean?> = mutableStateOf(false)
    val areReviews: MutableState<Boolean?> = mutableStateOf(false)
    val isVisitedUserLoaded: MutableState<Boolean> = mutableStateOf(false)
    var visitedUser: MutableState<UserBasicDTO?> = mutableStateOf(null)
    var isFollowing: MutableState<Boolean?> = mutableStateOf(null)
    private val visitedUserId: MutableState<String?> = mutableStateOf(null)

    var currentReviewPage: Int = 0
    var currentProductPage: Int = 0

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun setUserId(userId: String) {
        visitedUserId.value = userId
        updateUser()
        isVisitedUserLoaded.value = true
    }

    fun loadNextProductPage() {
        coroutineScope.launch {
            try {
                val newProducts = withContext(Dispatchers.IO) {
                    productControllerApi.getMyProducts(page = currentProductPage)
                }
                val productsToAdd = newProducts.content?.toList() ?: emptyList()
                productList.addAll(productsToAdd)
                currentProductPage++
                areProducts.value = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadNextReviewPage() {
        coroutineScope.launch {
            try {
                val newReviews = withContext(Dispatchers.IO) {
                    reviewControllerApi.allReviewReceived(userId = visitedUser.value?.id!!, page = currentProductPage)
                }
                val reviewsToAdd = newReviews.content?.toList() ?: emptyList()
                reviewList.addAll(reviewsToAdd)
                if(reviewsToAdd.isNotEmpty())
                    areReviews.value = true
                currentReviewPage++

                if (newReviews.last == true) {
                    coroutineScope.cancel()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun follow() {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    followingControllerApi.follow(userId = visitedUser.value?.id!!)
                }
                isFollowing.value = true
                visitedUser.value?.followersNumber = visitedUser.value?.followersNumber?.plus(1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        updateUser()
    }

    fun unfollow() {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    followingControllerApi.unfollow(userId = visitedUser.value?.id!!)
                }
                isFollowing.value = false
                visitedUser.value?.followersNumber = visitedUser.value?.followersNumber?.minus(1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        updateUser()
    }

    fun checkFollowing() {
        coroutineScope.launch {
            try {
                val following = withContext(Dispatchers.IO) {
                    followingControllerApi.imFollowingThisUser(userId = visitedUserId.value!!)
                }
                isFollowing.value = following
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateUser() {
        coroutineScope.launch {
            try {
                visitedUser.value = withContext(Dispatchers.IO) {
                    userControllerApi.userById(id = visitedUserId.value!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        checkFollowing()
    }

    fun report(description: String) {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    reportControllerApi.createReport(
                        body = ReportDTO(
                            reporterUser = CurrentDataUtils.currentUser as UserBasicDTO,
                            reportedUser = visitedUser.value,
                            description = description
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}