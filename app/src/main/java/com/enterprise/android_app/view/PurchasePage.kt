package com.enterprise.android_app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.view_models.DeliveryViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Phone
import io.swagger.client.models.AddressDTO

@Composable
fun PurchasePage(){


    Column() {
        Text("Shipping address")

        // ShippingCardReadOnly(address = )

    }

}

@Composable
fun ShippingCardReadOnly(address: AddressDTO){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            hoveredElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = address.header,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${address.street}, ${address.zipCode}")
            Text(text = "${address.city}, ${address.country}")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = FontAwesomeIcons.Solid.Phone,
                    contentDescription = "Phone Number",
                    modifier = Modifier.height(12.dp))
                Text(text = address.phoneNumber,modifier = Modifier.padding(start = 8.dp))

            }
        }
    }
}