package com.enterprise.android_app.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view.screen.filter.FilterScreen
import com.enterprise.android_app.view_models.SearchPageViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Filter
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SearchPage(){

    val searchPageViewModel: SearchPageViewModel = viewModel()

    var categories = searchPageViewModel.categories.collectAsState()

    val search = remember { searchPageViewModel.search }
    val filterPage: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }

    val products = searchPageViewModel.searchResults

    val lazyColumnState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val filter: MutableState<FilterOptions> = remember { searchPageViewModel.filter }

    if (filterPage.value)
        FilterScreen(viewModel = searchPageViewModel, onApply = {
            searchPageViewModel.search()
            filterPage.value = false
        })
    else {
        if (search.value) {
            searchPageViewModel.search()
            if (products.isNotEmpty()) {

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyGridProductsCard(products = products, lazyGridState = lazyGridState) {
                        searchPageViewModel.search()
                    }

                    FloatingActionButton(containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        shape = CircleShape,
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.BottomEnd)
                            .padding(13.dp),
                        onClick = { filterPage.value = true }) {
                        Icon(
                            FontAwesomeIcons.Solid.Filter,
                            contentDescription = stringResource(id = R.string.filter),
                            Modifier.size(20.dp)
                        )
                    }

                }
            } else
                CircularProgressIndicator(Modifier.size(40.dp))
        } else {
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
}