package com.enterprise.android_app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enterprise.android_app.view_models.OrdersViewModel
import io.swagger.client.models.OrderBasicDTO


@Composable
fun SingleOrderPage(navController: NavHostController, orderId: String){

    val orderViewModel: OrdersViewModel = viewModel()
    val order = orderViewModel.orderDTO.value


    LaunchedEffect(Unit) {
        orderViewModel.loadSingleOrder(orderId)
    }


    Column() {
        Text("Order ID: ${order?.id}")
        Text("Order Date: ${order?.orderDate}")

    }


}

