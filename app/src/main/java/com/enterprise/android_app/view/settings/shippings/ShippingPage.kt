package com.enterprise.android_app.view.settings.shippings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Map
import io.swagger.client.models.AddressDTO


@Composable
fun ShippingPage(navController: NavHostController) {
    val addresses=CurrentDataUtils.addresses

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Map,
                contentDescription = "Transport",
                modifier = Modifier.height(18.dp)
            )
            Text(
                text = stringResource(id = R.string.deliveryTo),
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            TransparentGreenButton(
                onClick = {
                    CurrentDataUtils.addressDTO.value = null
                    MainRouter.changePage(Navigation.AddEditShippingScreen)
                },
                modifier = Modifier.height(35.dp),
                buttonName = "Add new"
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(addresses) { item ->
                val address: MutableState<AddressDTO?> = remember {
                    mutableStateOf(item)
                }
                ShippingCard(address = address)
            }
        }
    }

}

/*
fun refreshList(addressDTO: AddressDTO) {
    CurrentDataUtils.addresses

    CurrentDataUtils.addresses.forEach { address ->
        if (address.id == addressDTO.id) {

            userAddresses.remove(address)
            userAddresses.add(addressDTO)
            return@forEach
        }
    }
}*/
