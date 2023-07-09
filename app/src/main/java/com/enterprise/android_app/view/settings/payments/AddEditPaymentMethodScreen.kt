package com.enterprise.android_app.view.settings.payments


import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.MainActivity
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view_models.PaymentViewModel
import java.time.LocalDate
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPaymentMethodScreen() {
    var payment = CurrentDataUtils.currentPaymentMethodDTO
    val paymentViewModel = PaymentViewModel()
    var creditCardText= remember { mutableStateOf(payment?.creditCard ?: "") }
    var expireDate= remember { mutableStateOf(payment?.expiryDate ?: LocalDate.now()) }
    var ownerText= remember { mutableStateOf(payment?.owner ?: "") }
    var isDefaultBoolean= remember { mutableStateOf(payment?.default ?: false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            TextField(
                value = creditCardText.value,
                onValueChange = { creditCardText.value = it },
                label = { Text("Credit Card Number") },
                modifier = Modifier.weight(1f)
            )
        }
        //Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            showDatePicker(context = LocalContext.current as Activity)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            TextField(
                value = ownerText.value,
                onValueChange = { ownerText.value = it },
                label = { Text("Owner") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp)
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            TransparentGreenButton(
                onClick = {
                    /*if(payment?.id!=null)
                    {

                        paymentViewModel.updatePayment(payment.copy(
                            creditCard = creditCardText.value,
                            expiryDate = expireDateText.value,
                            owner = ownerText.value,
                            default = isDefaultBoolean.value))

                        MainRouter.changePage(Navigation.ShippingPage)

                    } else {
                        paymentViewModel.createPayment(payment =  PaymentMethodCreateDTO(
                            creditCard = creditCardText.value,
                            expiryDate = expireDateText.value,
                            owner = ownerText.value,
                            default = isDefaultBoolean.value)
                        )
                        )
                    }*/
                },
                buttonName = if(payment?.id!=null)"Edit" else "Create"
            )
        }


    }

}

@Composable
fun showDatePicker(context: Context){

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Selected Date: ${date.value}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = "Open Date Picker")
        }
    }

}