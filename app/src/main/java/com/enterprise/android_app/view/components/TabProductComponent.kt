package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.view_models.ProductPageViewModel
import io.swagger.client.models.ProductDTO
import androidx.compose.foundation.layout.Column as Column


@Composable
fun TabProductComponent(productPageViewModel: ViewModel, navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    var productPageViewModel = remember { productPageViewModel as ProductPageViewModel }

    val lazyGridSellerState = rememberLazyGridState()
    val lazyGridSimilarState = rememberLazyGridState()

    val sellerProducts = productPageViewModel.sellerProducts
    val similarProducts = productPageViewModel.relatedProducts

    LaunchedEffect(key1 = productPageViewModel.currentSellerProductPage) {
        productPageViewModel.loadNextSellerProductPage()
        productPageViewModel.loadNextRelatedProductPage()

    }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.White
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                modifier = Modifier.height(48.dp)
            ) {
                Text(text = stringResource(R.string.seller_s_items))
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                modifier = Modifier.height(48.dp)
            ) {
                Text(text = stringResource(R.string.similar_items))
            }
        }

        // Content based on selected tab
        when (selectedTabIndex) {
            0 -> {
                Column(
                    Modifier.heightIn(max = 1300.dp)) {
                        LazyGridProductsCard(
                            products = sellerProducts,
                            lazyGridState = lazyGridSellerState,
                            navController = navController
                        ) {
                            productPageViewModel.loadNextRelatedProductPage()
                        }
                    }

            }
            1 -> {
                Column(
                    Modifier.heightIn(max = 1300.dp)) {
                    LazyGridProductsCard(
                        products = similarProducts,
                        lazyGridState = lazyGridSimilarState,
                        navController = navController
                    ) {
                        productPageViewModel.loadNextRelatedProductPage()
                    }
                }
            }

        }
    }
}
