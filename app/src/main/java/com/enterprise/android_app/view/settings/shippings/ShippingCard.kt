package com.enterprise.android_app.view.settings.shippings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.ui.theme.DarkGreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Edit
import compose.icons.fontawesomeicons.solid.Map
import compose.icons.fontawesomeicons.solid.Phone
import io.swagger.client.models.AddressDTO

@Composable
fun ShippingCard(address: AddressDTO){
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
                Column() {
                    IconButton(
                        onClick = { /*TODO*/ },
                        Modifier.border(BorderStroke(1.dp,Color.Green))
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Edit ,
                            contentDescription = "Edit ",
                            modifier = Modifier
                                .height(16.dp),

                        )
                    }
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
            Row( horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        //contentColor = DarkGreen
                    ),

                    elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp) ,
                    shape = MaterialTheme.shapes.small.copy(
                        topStart = CornerSize(8.dp),
                        topEnd = CornerSize(8.dp),
                        bottomStart = CornerSize(8.dp),
                        bottomEnd = CornerSize(8.dp)
                    ),
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                    border = BorderStroke(1.dp, DarkGreen),
                    modifier = Modifier.height(33.dp)

                )

                {
                    Text(
                        text = if(address.default == true)"Default Address" else "Set as default",
                        style = TextStyle(
                            color = (if(address.default==true) DarkGreen else Color.Red),
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }







        }
    }

}