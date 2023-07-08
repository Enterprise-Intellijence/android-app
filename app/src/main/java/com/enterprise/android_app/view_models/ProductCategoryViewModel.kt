package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductCategoryViewModel : ViewModel() {

    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var _primaryCategoryList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private var _secondaryCategoryList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private var _tertiaryCategoryList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    private var _categoryId = MutableStateFlow("")

    val primaryCategories: MutableStateFlow<List<String>>
        get() = _primaryCategoryList
    val secondaryCategories: MutableStateFlow<List<String>>
        get() = _secondaryCategoryList
    val tertiaryCategories: MutableStateFlow<List<String>>
        get() = _tertiaryCategoryList

    val categoryId: MutableStateFlow<String>
        get() = _categoryId

    fun getCategories() {
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

    fun getCategoryId(tertiaryCat: String) {
        coroutineScope.launch {
            try {
                val cat = withContext(Dispatchers.IO) {
                    productControllerApi.getCategoryId(tertiaryCat)
                }
                _categoryId.emit(cat)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        println("categoryId: " + _categoryId.value)
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