package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ProductCategoryDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductCategoryViewModel : ViewModel() {

    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var _primaryCategoryList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private var _secondaryCategoryList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private var _tertiaryCategoryList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    val primaryCategories: MutableStateFlow<List<String>>
        get() = _primaryCategoryList
    val secondaryCategories: MutableStateFlow<List<String>>
        get() = _secondaryCategoryList
    val tertiaryCategories: MutableStateFlow<List<String>>
        get() = _tertiaryCategoryList

    fun getCategories() {
        try {
            coroutineScope.launch {
                try {
                    val allCat = withContext(Dispatchers.IO) {
                        productControllerApi.getPrimaryCategoriesList()
                    }
                    _primaryCategoryList.emit(allCat as List<String>)
                } catch (e: Exception) {
                    println("error")
                    e.printStackTrace()
                }
            }
        }

        catch(e: Exception) {
            println("error")
            e.printStackTrace()
        }
    }

    fun getSecondaryCategories(primary: String) {

            coroutineScope.launch {
                try {
                    val categories = withContext(Dispatchers.IO) {
                        productControllerApi.getSecondaryCategoriesList(primary)
                    }
                    _secondaryCategoryList.emit(categories as List<String>)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    fun getTertiaryCategories(secondary: String) {
        coroutineScope.launch {
            try {
                val categories = withContext(Dispatchers.IO) {
                    productControllerApi.getTertiaryCategoriesList(secondary)
                }
                _tertiaryCategoryList.emit(categories as List<String>)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}