package com.enterprise.android_app.view_models

import ProductCategoryNode
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductCategoryDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductCategoryViewModel : ViewModel() {

    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var _allCategories = mutableStateOf<List<ProductCategoryDTO>?>(null)
    private var _categoriesMap = HashMap<String, ProductCategoryNode>()
    private var _primaryCategoryList = ArrayList<ProductCategoryNode>()


    fun getCategories() {
        try {
            coroutineScope.launch {
                var allCat = withContext(Dispatchers.IO) {
                    productControllerApi.getCategoriesList()
                }
                _allCategories = mutableStateOf<List<ProductCategoryDTO>?>(allCat)

                println("cat: " + _allCategories)
                _allCategories.value?.forEach { category ->
                    if (!_categoriesMap.containsKey(category.primaryCat)) {
                        var cat = ProductCategoryNode(
                            category.primaryCat,
                            category.primaryCat.replace("#.*".toRegex(), ""),
                            null,
                            ArrayList<ProductCategoryNode>()
                        )
                        println("cateogry: " + cat)
                        _categoriesMap.set(category.primaryCat, cat)
                        _primaryCategoryList.add(cat)
                    }
                    if (!_categoriesMap.containsKey(category.secondaryCat)) {
                        var cat = ProductCategoryNode(
                            category.secondaryCat,
                            category.secondaryCat.replace("#.*".toRegex(), ""),
                            _categoriesMap[category.primaryCat],
                            ArrayList<ProductCategoryNode>()
                        )
                        _categoriesMap[category.secondaryCat] = cat
                        _categoriesMap[category.primaryCat]?.addChildCategory(cat)
                    }
                    if (!_categoriesMap.containsKey(category.tertiaryCat)) {
                        var cat = ProductCategoryNode(
                            category.tertiaryCat,
                            category.tertiaryCat.replace("#.*".toRegex(), ""),
                            _categoriesMap[category.secondaryCat],
                            null
                        )
                        _categoriesMap[category.tertiaryCat] = cat
                        _categoriesMap[category.secondaryCat]?.addChildCategory(cat)
                    }
                }
            }
        }
        catch(e: Exception) {
            println("error")
            e.printStackTrace()
        }
    }

    var primaryCategories: ArrayList<ProductCategoryNode>? = null
        get() = _primaryCategoryList
}