package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.models.FilterOptions
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
    private var currentCategories: MutableList<String?> = mutableListOf(null, null, null)
    var tempSelectedCategories: MutableList<String?> = mutableListOf(null, null, null)
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
            currentCategories[0] = tempSelectedCategories[0]
            currentCategories[1] = tempSelectedCategories[1]
            currentCategories[2] = tempSelectedCategories[2]
            clearTempCategories()
            _search.value = true
            return
        }
        for(i in 0 until currentCategories.size) {
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
                if(i >= currentCategories.size - 1)
                {
                    currentCategories[0] = tempSelectedCategories[0]
                    currentCategories[1] = tempSelectedCategories[1]
                    currentCategories[2] = tempSelectedCategories[2]
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

    fun getSelectedCategories(): List<String?> {
        return currentCategories
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
                    productCategory = filter.value.productCategory, primaryCat = currentCategories[0],
                    secondaryCat = currentCategories[1], tertiaryCat = currentCategories[2],
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

    //TODO handle back and reset search boolean and selected categories
}