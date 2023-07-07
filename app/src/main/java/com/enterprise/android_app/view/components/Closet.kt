package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view_models.ProfileViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cubes


@Composable
fun Closet(visitedUserId: String) {
    val profileViewModel = remember {ProfileViewModel(visitedUserId)}
    val productList = profileViewModel.productList
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = profileViewModel.currentProductPage) {
        profileViewModel.loadNextProductPage()
    }

    if (!productList.isEmpty()) {
        LazyGridProductsCard(
            products = productList,
            lazyGridState = lazyGridState
        ) {
            profileViewModel.loadNextProductPage()
            productList.size
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                Icon(imageVector = FontAwesomeIcons.Solid.Cubes,
                    contentDescription = "Closet",
                    modifier = Modifier.size(80.dp))
            }

            if (visitedUserId == CurrentDataUtils.currentUser?.id) {
                Row() {
                    Text(
                        modifier = Modifier.size(20.dp),
                        text = "Your closet is empty!")
                }
                Row() {
                    Text(text = "Add some products to your closet")
                }

            } else {
                Row() {
                    Text(text = "This user's closet is empty")
                }
            }
        }
    }
}