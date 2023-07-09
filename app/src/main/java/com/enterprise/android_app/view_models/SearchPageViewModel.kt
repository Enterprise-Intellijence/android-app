package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view.screen.filter.Condition
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPageViewModel: ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val productController: ProductControllerApi = ProductControllerApi()
    private val categoryViewModel: ProductCategoryViewModel = ProductCategoryViewModel()
    private val _search: MutableState<Boolean> = mutableStateOf(false)
    private val _searchResults = mutableStateListOf<ProductBasicDTO> ()
    var tempSelectedCategories: MutableList<String?> = mutableListOf(null, null, null)
    val tempSelectedSize: SnapshotStateList<String?> = mutableStateListOf()
    var currentCat: MutableState<String?> = mutableStateOf(null)
    val selectedCondition: SnapshotStateList<Condition?> = mutableStateListOf()

    val filter: MutableState<FilterOptions> = mutableStateOf(FilterOptions())
    var currentSearchPage = 0
    var counter = 0

    private var _categories: MutableState<MutableStateFlow<List<String>>>

    val categories: MutableStateFlow<List<String>>
        get() = _categories.value

    val search: MutableState<Boolean>
        get() = _search

    val searchResults: SnapshotStateList<ProductBasicDTO>
        get() = _searchResults

    init {
        categoryViewModel.getCategories()
        _categories = mutableStateOf( categoryViewModel.primaryCategories )
    }
    fun selectCategory(category: String) {
        if(category == "All") {
            filter.value.primaryCat = tempSelectedCategories[0]
            filter.value.secondaryCat = tempSelectedCategories[1]
            filter.value.tertiaryCat = tempSelectedCategories[2]
            clearTempCategories()
            _search.value = true
            return
        }
        for(i in 0 until tempSelectedCategories.size) {
            if (tempSelectedCategories[i] == null) {
                tempSelectedCategories[i] = category
                when(i) {
                    0 -> {
                        categoryViewModel.getSecondaryCategories(category)
                        _categories.value = categoryViewModel.secondaryCategories
                    }
                    1 -> {
                        categoryViewModel.getTertiaryCategories(category)
                        _categories.value = categoryViewModel.tertiaryCategories
                    }
                }
                if(i >= tempSelectedCategories.size - 1)
                {
                    filter.value.primaryCat = tempSelectedCategories[0]
                    filter.value.secondaryCat = tempSelectedCategories[1]
                    filter.value.tertiaryCat = tempSelectedCategories[2]
                    clearTempCategories()
                    _search.value = true
                }else
                    _search.value = false
                break
            }
        }
    }


    fun clearTempCategories() {
        tempSelectedCategories[0] = null
        tempSelectedCategories[1] = null
        tempSelectedCategories[2] = null
    }

    fun clearTempSize() {
        tempSelectedSize[0] = null
        tempSelectedSize[1] = null
    }

    fun getPrimaryCategories(): MutableStateFlow<List<String>> {
        return categoryViewModel.primaryCategories
    }

    fun getSecondaryCategories(): MutableStateFlow<List<String>> {
        return categoryViewModel.secondaryCategories
    }

    fun getTertiaryCategories(): MutableStateFlow<List<String>> {
        return categoryViewModel.tertiaryCategories
    }

    fun search() {
        currentCat.value = null
        tempSelectedSize.clear()
        selectedCondition.clear()
        _categories.value = categoryViewModel.primaryCategories
        try {
            coroutineScope.launch {
                val searched = withContext(Dispatchers.IO) { productController.getFilteredProducts(
                    title = filter.value.title, description = filter.value.description,
                    minProductCost = filter.value.minProductCost as Double?,
                    maxProductCost = filter.value.maxProductCost as Double?,
                    brands = filter.value.brands, condition = filter.value.condition,
                    views = filter.value.views as Int?, userId = filter.value.userId,
                    uploadDate = filter.value.uploadDate, availability = filter.value.availability,
                    productCategory = filter.value.productCategory, primaryCat = filter.value.primaryCat,
                    secondaryCat = filter.value.secondaryCat, tertiaryCat = filter.value.tertiaryCat,
                    likesNumber = filter.value.likesNumber as Int?, productGender = filter.value.productGender,
                    sizes = filter.value.sizes, colour = filter.value.colour,
                    entertainmentLanguage = filter.value.entertainmentLanguage,
                    sortBy = filter.value.sortBy, sortDirection = filter.value.sortDirection,
                    homeMaterial = filter.value.homeMaterial, page = currentSearchPage, sizePage = 20) }
                _searchResults.addAll(searched.content?.toList() ?: emptyList())
                currentSearchPage++
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun clearFilter() {
        filter.value = FilterOptions()
        currentSearchPage = 0
        _categories.value = categoryViewModel.primaryCategories
        _searchResults.clear()
        filter.value.sortBy = "views"
        _search.value = true
    }
}