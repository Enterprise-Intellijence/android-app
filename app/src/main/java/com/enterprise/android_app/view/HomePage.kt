package com.enterprise.android_app.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view_models.HomePageViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun HomePage(){
/*    val homePageViewModel = remember { HomePageViewModel() }

    val productList = homePageViewModel._productList

    LaunchedEffect(key1 = homePageViewModel.currentPage) {
        homePageViewModel.loadNextPage()
    }

    LazyGridProductsCard(products = productList, onLoadMore = { CoroutineScope(Dispatchers.IO).launch{ homePageViewModel.loadNextPage() } })*/


}