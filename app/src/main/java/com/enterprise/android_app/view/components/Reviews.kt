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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view_models.ProfileViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bars

@Composable
fun Reviews(viewModel: ProfileViewModel, navController: NavController) {
    val reviewList = remember { viewModel.reviewList }
    val lazyGridState = rememberLazyGridState()
    val areReviews = remember { viewModel.areReviews }

    LaunchedEffect(key1 = viewModel.currentReviewPage) {
        viewModel.loadNextReviewPage()
    }

    if (!reviewList.isEmpty()) {
        LazyGridReviewsCard(
            reviews = reviewList,
            lazyGridState = lazyGridState,
            navController = navController,
        ) {
            viewModel.loadNextReviewPage()
            reviewList.size
        }
    } else {
        if (areReviews.value == true) {
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row() {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Bars,
                        contentDescription = "Reviews",
                        modifier = Modifier.size(60.dp)
                    )
                }

                if (viewModel.visitedUser.value?.id == CurrentDataUtils.currentUser?.id) {
                    Row() {
                        Text(text = "You have no reviews yet!")
                    }
                } else {
                    Row() {
                        Text(text = "This user has no reviews")
                    }
                }
            }
        }
    }
}