package com.enterprise.android_app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view_models.SearchPageViewModel
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SearchPage(){

    val searchPageViewModel: SearchPageViewModel = viewModel()

    var categories = searchPageViewModel.categories.collectAsState()

    val search = remember { searchPageViewModel.search }

    val products = searchPageViewModel.searchResults

    val lazyColumnState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()

    if (search.value) {
        searchPageViewModel.search()
            Column(modifier = Modifier.fillMaxSize()) {
                LazyGridProductsCard(products = products, lazyGridState = lazyGridState) {
                    searchPageViewModel.search()
                }
        }
    }else {
        LazyColumn(state = lazyColumnState) {
            item {
                SingleRowTemplate(name = "All", icona = null, icon_label = null,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { searchPageViewModel.selectCategory("All") })
            }
            for (category in categories.value) {
                item {
                    SingleRowTemplate(name = category, icona = null, icon_label = null,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { searchPageViewModel.selectCategory(category) })
                }
            }
        }
    }

}