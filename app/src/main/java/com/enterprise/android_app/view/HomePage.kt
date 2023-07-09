package com.enterprise.android_app.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view_models.HomePageViewModel


@Composable
fun HomePage(){
    val homePageViewModel: HomePageViewModel = viewModel()

    val productList = homePageViewModel.productList
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = homePageViewModel.currentPage) {
        homePageViewModel.loadNextPage()
    }

    Column(Modifier.padding(top = 10.dp).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        if (productList.isEmpty()) {
            CircularProgressIndicator(Modifier.size(40.dp))
        } else {
            LazyGridProductsCard(
                products = productList,
                lazyGridState = lazyGridState
            )
            {
                homePageViewModel.loadNextPage()
                val contentSize = productList.size
                Log.d("HomePage", "Content size: $contentSize")
            }
        }
    }

}

@Preview
@Composable
fun HomePagePreview() {
    AndroidappTheme {
        HomePage()
    }
}