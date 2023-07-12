package com.enterprise.android_app.view.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view_models.OrdersViewModel

import io.swagger.client.models.OrderBasicDTO


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCard(order: OrderBasicDTO, navController: NavHostController) {
    Card(
        modifier = Modifier
            .size(width = 100.dp, height = 150.dp)
            .background(color = Color.Transparent)
            .clickable{
                navController.navigate(Navigation.SingleOrderPage.route+"?orderId=${order.id}")},
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = order.product.productImages?.get(0)?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
                                transformations(RoundedCornersTransformation(8f))
                            }).build()
                    ),
                    contentDescription = "Product image",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = order.product.title,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ship Date: ${order.orderDate}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Order State: ${order.state.value}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}



