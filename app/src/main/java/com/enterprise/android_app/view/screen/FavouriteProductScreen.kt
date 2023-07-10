package com.enterprise.android_app.view.screen

import android.util.Log
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view_models.FavouriteViewModel
import com.enterprise.android_app.view_models.HomePageViewModel

@Composable
fun FavouriteProductScreen(navController: NavHostController){
    val favouriteViewModel = remember { FavouriteViewModel() }

    val productList = favouriteViewModel.productList
    val lazyGridState = rememberLazyGridState()


    LaunchedEffect(key1 = favouriteViewModel.currentPage) {
        favouriteViewModel.loadNextPage()

    }

    LazyGridProductsCard(
        products = productList,
        lazyGridState = lazyGridState

    )
    {
        favouriteViewModel.loadNextPage()
        val contentSize = productList.size
    }

}