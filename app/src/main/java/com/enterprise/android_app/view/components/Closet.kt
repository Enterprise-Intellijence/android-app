package com.enterprise.android_app.view.components

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.enterprise.android_app.view_models.ProfileViewModel


@Composable
fun Closet(visitedUserId: String) {
    val profileViewModel = remember {ProfileViewModel(visitedUserId)}
    val productList = profileViewModel.productList
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = profileViewModel.currentPage) {
        profileViewModel.loadNextPage()
    }

    LazyGridProductsCard(
        products = productList,
        lazyGridState = lazyGridState
    ) {
        profileViewModel.loadNextPage()
        val contentSize = productList.size
    }
}