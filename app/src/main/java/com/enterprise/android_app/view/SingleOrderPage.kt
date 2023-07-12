package com.enterprise.android_app.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import io.swagger.client.models.OrderBasicDTO


@Composable
fun SingleOrderPage(navController: NavHostController, orderId: String){


    Text("$orderId")
}