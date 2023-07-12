package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view_models.ProfileViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cubes


@Composable
fun Closet(viewModel: ProfileViewModel, navController: NavHostController) {
    val productList = viewModel.productList
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = "Closet") {
        viewModel.loadNextProductPage()
    }

    if (!productList.isEmpty()) {
        LazyGridProductsCard(
            products = productList,
            lazyGridState = lazyGridState,
            navController = navController
        ) {
            viewModel.loadNextProductPage()

            productList.size
        }
    } else {

        if (!viewModel.areProducts.value!!) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row() {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Cubes,
                        contentDescription = "Closet",
                        modifier = Modifier.size(60.dp)
                    )
                }

                if (viewModel.visitedUser.value?.id == CurrentDataUtils.currentUser?.id) {
                    Row() {
                        Text(
                            modifier = Modifier.size(20.dp),
                            text = "Your closet is empty!"
                        )
                    }
                    Row() {
                        Text(text = "Start to sell a product by clicking the + icon below")
                    }

                } else {
                    Row() {
                        Text(text = "This closet is empty")
                    }
                }
            }
        }
    }
}