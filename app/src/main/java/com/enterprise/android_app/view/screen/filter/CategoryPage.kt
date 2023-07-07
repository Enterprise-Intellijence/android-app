package com.enterprise.android_app.view.screen.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view.CategorySelection
import com.enterprise.android_app.view_models.SearchPageViewModel
import io.swagger.client.apis.ProductControllerApi

@Composable
fun CategoryPage( viewModel: SearchPageViewModel, onApply: () -> Unit) {
    CategorySelection(searchPageViewModel = viewModel, onApply = onApply)
}