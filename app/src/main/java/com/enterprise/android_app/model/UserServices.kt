package com.enterprise.android_app.model

import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UserServices {
    private val userControllerApi = UserControllerApi()

    private var _likedProducts: MutableList<String> = mutableListOf()

    var likedProducts: MutableList<String> = mutableListOf()
        get() = _likedProducts

    fun retriveLikedProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _likedProducts =
                    userControllerApi.getLikedProducts(0, 100)!!.content!!.map { it.id!! }
                        .toMutableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

        fun isProductLiked(productId: String): Boolean {
            return likedProducts.contains(productId)
        }
    }