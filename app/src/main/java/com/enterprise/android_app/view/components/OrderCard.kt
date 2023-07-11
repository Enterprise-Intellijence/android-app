package com.enterprise.android_app.view.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.OrderDTO

@Composable
fun OrderCard(order: OrderBasicDTO){
    Card(
        modifier = Modifier
            .size(width = 100.dp, height = 250.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column() {
            Text(order.id)
            Spacer(modifier = Modifier.padding(5.dp))
            Text("fine card -->  " + order.orderDate)
        }


    }
}