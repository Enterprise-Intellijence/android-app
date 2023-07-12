package com.enterprise.android_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enterprise.android_app.view_models.OrdersViewModel
import io.swagger.client.models.OrderBasicDTO


@Composable
fun SingleOrderPage(navController: NavHostController, orderId: String){

    val orderViewModel: OrdersViewModel = viewModel()
    val order = orderViewModel.orderDTO

    LaunchedEffect( "singleOrderPage" ) {
        orderViewModel.loadSingleOrder(orderId)
    }

    if (order.value == null) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(Modifier.size(40.dp))
        }
    } else {
        Column(Modifier.fillMaxSize()) {
            Text("Order ID: ${order.value?.id}")
            Text("Order Date: ${order.value?.orderDate}")

        }
    }

}

