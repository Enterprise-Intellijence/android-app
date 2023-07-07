package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.enterprise.android_app.view_models.ProfileViewModel

@Composable
fun Reviews(visitedUserId: String) {
    val profileViewModel = remember { ProfileViewModel(visitedUserId) }
    val reviewList = profileViewModel.reviewList
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = profileViewModel.currentReviewPage) {
        profileViewModel.loadNextReviewPage()
    }

    if (!reviewList.isEmpty()) {
        LazyGridReviewsCard(
            reviews = reviewList,
            lazyGridState = lazyGridState
        ) {
            profileViewModel.loadNextReviewPage()
            reviewList.size
        }
    } else {
        Row() {
            Text(text = "No reviews yet")
        }
    }
}