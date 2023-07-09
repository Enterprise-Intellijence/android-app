package com.enterprise.android_app.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UserServices {
    private val userControllerApi = UserControllerApi()

    private var _likedProducts: SnapshotStateList<String> = mutableStateListOf()

    var likedProducts: SnapshotStateList<String> = mutableStateListOf()
        get() = _likedProducts

    fun retriveLikedProducts(i:Int = 0) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var liked = userControllerApi.getLikedProducts(i, 100)
                _likedProducts.addAll( liked.content!!.map { it.id!! })
                if(liked.last == false)
                    retriveLikedProducts(i+1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addLikedProduct(id: String) {
        _likedProducts.add(id)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = userControllerApi.like(id)
            } catch (e: Exception) {
                e.printStackTrace()
                _likedProducts.remove(id)
            }
        }
    }

    fun removeLikedProduct(id: String) {
        _likedProducts.remove(id)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = userControllerApi.unlike(id)
            } catch (e: Exception) {
                e.printStackTrace()
                _likedProducts.add(id)
            }
        }
    }

        fun isProductLiked(productId: String): Boolean {
            return likedProducts.contains(productId)
        }
    }