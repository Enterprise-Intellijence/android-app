package com.enterprise.android_app.view.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import io.swagger.client.models.OrderBasicDTO


@Composable
fun OrderCard(order: OrderBasicDTO) {
    Card(
        modifier = Modifier
            .size(width = 100.dp, height = 150.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberImagePainter(
                        data = order.product.productImages?.get(0)?.urlPhoto,
                        builder = {
                            transformations(RoundedCornersTransformation(8f))
                        }
                    ),
                    contentDescription = "Product image",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = order.product.title,
                    style = MaterialTheme.typography.labelSmall,    //sottotitolo
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ship Date: ${order.orderDate}",
                style = MaterialTheme.typography.bodyMedium          //body 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Order State: ${order.state.value}",
                style = MaterialTheme.typography.bodyMedium        //body 1
            )
        }
    }
}



