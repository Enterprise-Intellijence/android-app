package com.enterprise.android_app.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.components.LazyGridProductsCard
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductBasicDTO

@Composable
fun HomePage(){
    val productsList = remember { mutableStateListOf<ProductBasicDTO>() }
    val currentPage = remember { mutableStateOf(0) }
    val productControllerApi : ProductControllerApi = ProductControllerApi()

    LaunchedEffect(currentPage.value) {
        val newProducts = productControllerApi.getFilteredProducts(page = currentPage.value)
        newProducts.content?.let{productsList.addAll(it)}
    }

    LazyGridProductsCard(products = productsList) {
        currentPage.value++
    }


}