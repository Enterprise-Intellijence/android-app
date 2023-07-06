package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class SearchPageViewModel: ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val productController: ProductControllerApi = ProductControllerApi()
    private val categoryViewModel: ProductCategoryViewModel = ProductCategoryViewModel()
    private val _search: MutableState<Boolean> = mutableStateOf(false)
    private val _searchResults = mutableStateListOf<ProductBasicDTO>()
    private var currentCategories: MutableList<String?> = mutableListOf(null, null, null)
    var currentSearchPage = 0

    private lateinit var _categories: MutableState<MutableStateFlow<List<String>>>

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
            _search.value = true
            return
        }
        for(i in 0 until currentCategories.size) {
            if (currentCategories[i] == null) {
                currentCategories[i] = category
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
                _search.value = i >= currentCategories.size - 1
                break
            }
        }
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
        try {
            coroutineScope.launch {
                val searched = withContext(Dispatchers.IO) { productController.getFilteredProducts(
                    primaryCat = currentCategories[0],
                    secondaryCat = currentCategories[1],
                    tertiaryCat = currentCategories[2], page = currentSearchPage) }
                _searchResults.addAll(searched.content?.toList() ?: emptyList())
                currentSearchPage++
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    //TODO handle back and reset search boolean and selected categories
}