package com.enterprise.android_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.view_models.OrdersViewModel
import io.swagger.client.models.OrderBasicDTO


@Composable
fun SingleOrderPage(navController: NavHostController, orderId: String) {
    val orderViewModel: OrdersViewModel = viewModel()
    val order = orderViewModel.orderDTO

    LaunchedEffect("singleOrderPage") {
        orderViewModel.loadSingleOrder(orderId)
    }

    val isLoading = order.value == null

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentSize())
        }
    } else {
        val currentOrder = order.value!!

        val colors = MaterialTheme.colorScheme.onSurface

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = order.value!!.product.productImages?.get(0)?.urlPhoto)
                                .apply {
                                    transformations(RoundedCornersTransformation(8f))
                                }
                                .build()
                        ),
                        contentDescription = "Product image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = currentOrder.product.title,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f)
                    )
                }

                Divider(
                    color = colors.copy(alpha = 0.12f),
                    thickness = 15.dp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Column {
                    Text(
                        text = "Order State:",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = colors
                    )

                    Text(
                        text = currentOrder.state.value,
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = colors
                    )

                    Text(
                        text = "Delivery Address:",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = colors
                    )

                    currentOrder.deliveryAddress?.let { address ->
                        Text(
                            text = "${address.city}, ${address.country}",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(bottom = 4.dp),
                            color = colors
                        )
                    }

                    Text(
                        text = "Payment Card:",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = colors
                    )

                    currentOrder.transaction?.let { transaction ->
                        Text(
                            text = transaction.paymentMethod,
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(bottom = 4.dp),
                            color = colors
                        )
                    }

                    Text(
                        text = "Price:",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = colors
                    )

                    currentOrder.transaction?.amount?.let { amount ->
                        Text(
                            text = "${amount.price}",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(bottom = 4.dp),
                            color = colors
                        )
                    }
                }
            }
        }
    }
}










