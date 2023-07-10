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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.MainActivity
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view_models.PaymentViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Edit
import io.swagger.client.models.PaymentMethodCreateDTO
import java.time.LocalDate
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPaymentMethodScreen() {
    val payment = CurrentDataUtils.currentPaymentMethodDTO
    val paymentViewModel = PaymentViewModel()
    val creditCardText= remember { mutableStateOf(payment.value?.creditCard ?: "") }
    val expireDate= remember { mutableStateOf(payment.value?.expiryDate ?: LocalDate.now()) }
    val ownerText= remember { mutableStateOf(payment.value?.owner ?: "") }
    val isDefaultBoolean = remember { mutableStateOf((payment.value?.default ?: false) as Boolean) }

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
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically ,modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Calendar ,
                contentDescription = "Exp Date ",
                modifier = Modifier
                    .height(20.dp),

                )
            Text(text = expireDate.value.toString(),
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp).weight(1f)



            )
            showDatePicker(context = LocalContext.current as Activity, date = expireDate)
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            TransparentGreenButton(
                onClick = {
                    if(payment.value?.id!=null)
                    {

                        paymentViewModel.updatePayment(payment.value?.copy(
                            creditCard = creditCardText.value,
                            expiryDate = expireDate.value,
                            owner = ownerText.value,
                            default = isDefaultBoolean.value)!!)

                        //MainRouter.changePage(Navigation.PaymentsPage)

                    } else {
                        paymentViewModel.createPayment(payment =  PaymentMethodCreateDTO(
                            creditCard = creditCardText.value,
                            expiryDate = expireDate.value,
                            owner = ownerText.value,
                            isDefault = isDefaultBoolean.value as Boolean
                        )
                        )

                    }
                },
                buttonName = if(payment.value?.id!=null)"Edit" else "Create"
            )
        }
        //Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.setAsDefault))
            RadioButton(selected = isDefaultBoolean.value, onClick = { isDefaultBoolean.value = !isDefaultBoolean.value })
        }



    }

}

@Composable
fun showDatePicker(context: Context, date: MutableState<LocalDate> ){

/*    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(date.value.year)
    month = calendar.get(date.value.month.value)
    day = calendar.get(date.value.dayOfMonth)
    calendar.time = Date()*/
    val calendar = Calendar.getInstance()
    calendar.time = Date.from(date.value.atStartOfDay().toInstant(java.time.ZoneOffset.UTC))

/*
    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = LocalDate.of(year,month,dayOfMonth) *//*"$dayOfMonth/$month/$year"*//*
        }, year, month, day
    )*/

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = LocalDate.of(year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

/*    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {*/

        //Text(text = "Selected Date: ${date.value}")
        //Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = "Change Exp Date")
        }
    //}

}