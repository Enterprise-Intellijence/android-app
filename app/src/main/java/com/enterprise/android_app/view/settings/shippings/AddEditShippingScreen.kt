package com.enterprise.android_app.view.settings.shippings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view_models.DeliveryViewModel
import io.swagger.client.models.AddressCreateDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditShippingScreen(navController: NavHostController) {
    var address = CurrentDataUtils.addressDTO
    val deliveryViewModel = DeliveryViewModel()
    val headerText= remember {mutableStateOf(address.value?.header ?: "")}
    val streetText= remember {mutableStateOf(address.value?.street ?: "")}
    val zipCodeText= remember {mutableStateOf(address.value?.zipCode ?: "")}
    val cityText= remember {mutableStateOf(address.value?.city ?: "")}
    val countryText= remember {mutableStateOf(address.value?.country ?: "")}
    val phoneNumber= remember {mutableStateOf(address.value?.phoneNumber ?: "")}
    val isDefaultBoolean= remember {mutableStateOf((address.value?.isDefault ?: false) as Boolean)}


    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            TextField(
                value = headerText.value,
                onValueChange = { headerText.value = it },
                label = { Text("Header") },
                modifier = Modifier.weight(1f)
            )
        }
        //Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            TextField(
                value = streetText.value,
                onValueChange = { streetText.value = it },
                label = { Text("Street") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp)
            )
            TextField(
                value = zipCodeText.value,
                onValueChange = { zipCodeText.value = it },
                label = { Text("Zip code") },
                modifier = Modifier.width(100.dp)
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            TextField(
                value = cityText.value,
                onValueChange = { cityText.value = it },
                label = { Text("City") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp)
            )
            TextField(
                value = countryText.value,
                onValueChange = { countryText.value = it },
                label = { Text("Country") },
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)
        ) {
            TextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp)
            )
            Column() {
                Text(text = stringResource(id = R.string.setAsDefault))
                RadioButton(selected = isDefaultBoolean.value, onClick = { isDefaultBoolean.value = !isDefaultBoolean.value })

            }
        }
        
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 40.dp, bottom = 15.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            TransparentGreenButton(
                onClick = {
                    if(address.value?.id!=null)
                    {

                        deliveryViewModel.updateAddress(address.value?.copy(header = headerText.value,
                            street = streetText.value,
                            zipCode = zipCodeText.value,
                            city = cityText.value,
                            country = countryText.value,
                            phoneNumber = phoneNumber.value,
                            isDefault = isDefaultBoolean.value)!!)

                        MainRouter.changePage(Navigation.ShippingPage)

                    } else {
                        deliveryViewModel.createAddress(createAddressDTO = AddressCreateDTO(
                            header = headerText.value,
                            street = streetText.value,
                            zipCode = zipCodeText.value,
                            city = cityText.value,
                            country = countryText.value,
                            phoneNumber = phoneNumber.value,
                            isDefault = isDefaultBoolean.value))
                    }
                          },
                buttonName = if(address.value?.id!=null)"Edit" else "Create"
            )
        }


    }
}

