package com.enterprise.android_app.model

import io.swagger.client.apis.UserControllerApi

object UserServices {
    private val userControllerApi = UserControllerApi()

    private var _likedProducts: MutableList<String> = mutableListOf()

    var likedProducts: MutableList<String> = mutableListOf()
        get() = _likedProducts

    fun retriveLikedProducts() {
        _likedProducts = userControllerApi.getLikedProducts(0, 100)!!.content!!.map { it.id!! }.toMutableList()
    }

    fun isProductLiked(productId: String): Boolean {
        return likedProducts.contains(productId)
    }
}