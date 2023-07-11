package com.enterprise.android_app.view.settings.payments

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.DarkGreen
import com.enterprise.android_app.view_models.DeliveryViewModel
import com.enterprise.android_app.view_models.PaymentViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Edit
import compose.icons.fontawesomeicons.solid.PersonBooth
import compose.icons.fontawesomeicons.solid.Trash
import io.swagger.client.models.PaymentMethodDTO

@Composable
fun PaymentsMethodCard(payment: MutableState<PaymentMethodDTO?> ){
    val paymentViewModel = PaymentViewModel()

    val mContext = LocalContext.current

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
                        text = payment.value?.creditCard !!,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    )
                }
                Column() {
                    Row() {
                        IconButton(
                            onClick = {
                                EditPayment(payment = payment)
                                MainRouter.changePage(Navigation.AddEditPaymentMethodScreen)
                            },
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Edit ,
                                contentDescription = "Edit ",
                                modifier = Modifier
                                    .height(16.dp),

                                )
                        }
                        IconButton(
                            onClick = {
                                payment.value?.id?.let { paymentViewModel.deletePayment(it) }

                            }

                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Trash,
                                contentDescription = "Remove",
                                modifier = Modifier
                                    .height(16.dp)
                            )

                        }
                    }

                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = FontAwesomeIcons.Solid.Calendar,
                    contentDescription = "Phone Number",
                    modifier = Modifier.height(12.dp))
                Text(text = stringResource(id = R.string.expDate)+" ${payment.value?.expiryDate}")

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = FontAwesomeIcons.Solid.PersonBooth,
                    contentDescription = "Owner",
                    modifier = Modifier.height(12.dp))
                Text(text = stringResource(id = R.string.owner)+" ${payment.value?.owner}")

            }
            if(payment.value?.isDefault==true){
                Text(text = stringResource(id = R.string.defaultPaymentMethod), style = TextStyle(color = DarkGreen, fontWeight = FontWeight.Bold))
            }
            /*else{
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f,true))
                    Button(
                        onClick = {
                            changeDefaultPayment(payment = payment.value?.copy(default = true) !!)
                            mToast(context = mContext, "Address default changed")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),

                        elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp) ,
                        shape = MaterialTheme.shapes.small.copy(
                            topStart = CornerSize(8.dp),
                            topEnd = CornerSize(8.dp),
                            bottomStart = CornerSize(8.dp),
                            bottomEnd = CornerSize(8.dp)
                        ),
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                        border = BorderStroke(1.dp, Color.Red),
                        modifier = Modifier.height(33.dp)
                    )

                    {
                        Text(
                            text = stringResource(id = R.string.setAsDefault),
                            style = TextStyle(
                                color = Color.Red,
                                fontStyle = FontStyle.Italic
                            )
                        )
                    }
                }

            }*/
        }
    }

}

private fun changeDefaultPayment(payment: PaymentMethodDTO){
    val paymentViewModel = PaymentViewModel()
    paymentViewModel.updatePayment(payment = payment)
}

private fun mToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

private fun EditPayment(payment: MutableState<PaymentMethodDTO?>) {
    CurrentDataUtils.currentPaymentMethodDTO = payment
}