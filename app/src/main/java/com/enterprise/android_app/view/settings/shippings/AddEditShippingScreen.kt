package com.enterprise.android_app.view.settings.shippings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view_models.DeliveryViewModel
import io.swagger.client.models.AddressCreateDTO
import io.swagger.client.models.AddressDTO
import io.swagger.client.models.UserDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditShippingScreen(){
    var address = CurrentDataUtils.addressDTO
    val deliveryViewModel = DeliveryViewModel()
    var headerText= remember {mutableStateOf(address?.header ?: "")}
    var streetText= remember {mutableStateOf(address?.street ?: "")}
    var zipCodeText= remember {mutableStateOf(address?.zipCode ?: "")}
    var cityText= remember {mutableStateOf(address?.city ?: "")}
    var countryText= remember {mutableStateOf(address?.country ?: "")}
    var phoneNumber= remember {mutableStateOf(address?.phoneNumber ?: "")}
    var isDefaultBoolean= remember {mutableStateOf(address?.default ?: false)}


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
                    if(address?.id!=null)
                    {

                        deliveryViewModel.updateAddress(address.copy(header = headerText.value,
                            street = streetText.value,
                            zipCode = zipCodeText.value,
                            city = cityText.value,
                            country = countryText.value,
                            phoneNumber = phoneNumber.value,
                            default = isDefaultBoolean.value))

                        MainRouter.changePage(Navigation.ShippingPage)

                    } else {
                        deliveryViewModel.createAddress(createAddressDTO = AddressCreateDTO(
                            header = headerText.value,
                            street = streetText.value,
                            zipCode = zipCodeText.value,
                            city = cityText.value,
                            country = countryText.value,
                            phoneNumber = phoneNumber.value,
                            default = isDefaultBoolean.value))
                    }
                          },
                buttonName = if(address?.id!=null)"Edit" else "Create"
            )
        }


    }
}

private fun updateValue(addressDTO: AddressDTO, headerText: String,streetText: String,zipCodeText: String,cityText: String,countryText: String,phoneNumber: String,isDefault: Boolean ): AddressDTO {
    return addressDTO.copy(
        header = headerText,
        street = streetText,
        zipCode = zipCodeText,
        city = cityText,
        country = countryText,
        phoneNumber = phoneNumber,
        default = isDefault

        )
}