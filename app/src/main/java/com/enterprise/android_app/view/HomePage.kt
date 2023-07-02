package com.enterprise.android_app.view

import android.util.Log
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view_models.HomePageViewModel


@Composable
fun HomePage(){
    val homePageViewModel = remember { HomePageViewModel() }

    val productList = homePageViewModel.productList
    val lazyGridState = rememberLazyGridState()


    LaunchedEffect(key1 = homePageViewModel.currentPage) {
        homePageViewModel.loadNextPage()

    }

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