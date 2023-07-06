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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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

    val products = searchPageViewModel.searchResults

    val lazyColumnState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val filter: MutableState<FilterOptions> = remember { searchPageViewModel.filter }

    if (search.value) {
        searchPageViewModel.search()
        if (products != null) {

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyGridProductsCard(products = products, lazyGridState = lazyGridState) {
                        searchPageViewModel.search()
                    }
                    Row( verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End) {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.outlinedButtonColors(),
                            modifier = Modifier
                                .size(40.dp)
                                .padding()
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.primary
                                ).clip(CircleShape),
                        ) {
                            Icon(
                                FontAwesomeIcons.Solid.Filter,
                                contentDescription = stringResource(id = R.string.filter)
                            )
                        }
                    }

            }
        }else
            CircularProgressIndicator(Modifier.size(40.dp))
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