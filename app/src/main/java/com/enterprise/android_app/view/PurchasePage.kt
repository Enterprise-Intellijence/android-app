package com.enterprise.android_app.view

import PurchasePageViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.DarkGreen
import com.enterprise.android_app.view_models.ProductPageViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.CreditCard
import compose.icons.fontawesomeicons.solid.Map
import compose.icons.fontawesomeicons.solid.PersonBooth
import compose.icons.fontawesomeicons.solid.Phone
import io.swagger.client.models.AddressDTO
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.OfferBasicDTO
import io.swagger.client.models.OrderCreateDTO
import io.swagger.client.models.OrderDTO
import io.swagger.client.models.PaymentMethodDTO
import io.swagger.client.models.ProductDTO
import java.time.LocalDateTime

@Composable
fun PurchasePage(navController: NavHostController){

    val context = LocalContext.current
    val productViewModel: ProductPageViewModel = viewModel()
    val orderViewModel: PurchasePageViewModel = viewModel()
    productViewModel.getProductById(CurrentDataUtils.currentProductId)

    val product = productViewModel.product

    if(CurrentDataUtils.addressDTO.value == null)
        CurrentDataUtils.addressDTO = CurrentDataUtils.defaultAddress

    println("addressessss: " + CurrentDataUtils.addresses.toList())

    if(CurrentDataUtils.currentPaymentMethodDTO.value == null)
        CurrentDataUtils.currentPaymentMethodDTO = CurrentDataUtils.defaultPaymentMethod

    Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp).verticalScroll(rememberScrollState())) {

        if (product != null) {
            ProductView(product)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Shipping address",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))

        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        CurrentDataUtils.addressDTO.let { it.value?.let { it1 -> ShippingCardReadOnly(it1, Modifier.fillMaxWidth()) } }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*MainRouter.changePage(Navigation.SelectAddressPage)*/ }) {
            Text("Change address")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Payment method",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))

        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        CurrentDataUtils.currentPaymentMethodDTO.let { it.value?.let { it1 ->
            PaymentMethodCardReadOnly(
                it1, Modifier.fillMaxWidth())
        } }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*MainRouter.changePage(Navigation.SelectPaymentPage)*/ }) {
            Text("Change payment method")
        }
        Row {
            Spacer(modifier = Modifier
                .weight(1f)
                .padding(top = 8.dp, end = 8.dp))
            Button(onClick = {
                val offer: OfferBasicDTO? = product?.deliveryCost?.price?.let {
                    product.productCost.price?.plus(it)
                }?.let {
                    product?.productCost?.currency?.let { it1 ->
                        CustomMoneyDTO(
                            it, it1
                        )
                    }
                }?.let { OfferBasicDTO(null, it, null, LocalDateTime.now()) }

                val order: OrderCreateDTO? = product?.let {
                    CurrentDataUtils.toProductBasicDTO(it) }?.let { CurrentDataUtils.addressDTO.value?.let { it1 ->
                    OrderCreateDTO(it, offer, it1) }
                    }
                orderViewModel.createOrder(order, context)

                // TODO: routing a lista di ordini
            }) {
                Text("Buy now")
            }
        }
    }
}

@Composable
fun ProductView(product: ProductDTO) {
    Text("Order",
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
        modifier = Modifier.padding(8.dp))

    Divider()
    Spacer(modifier = Modifier.height(16.dp))

    Card(modifier = Modifier
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(hoveredElevation = 5.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.fillMaxWidth()) {
            Text("${product.title}", modifier = Modifier.padding(4.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(2.dp)) {
                Text("Product cost: ")
                Text("${product.productCost.price} ${product.productCost.currency}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(2.dp)) {
                Text("Delivery cost: ")
                Text("${product.deliveryCost.price} ${product.deliveryCost.currency}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))

            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(2.dp)) {
                Text("Total cost: ")
                Text("${product.productCost.price?.plus(product.deliveryCost.price!!)} ${product.productCost.currency}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SelectAddressPage(){
    val addresses = CurrentDataUtils.addresses

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
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(addresses) { item ->
                ShippingCardReadOnly(address = item, Modifier
                    .fillMaxWidth()
                    .clickable {
                        CurrentDataUtils.addressDTO.value = item
                    })
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /*MainRouter.changePage(Navigation.PurchasePage)*/ }) {
            Text(text = "Confirm")
        }
    }
}

@Composable
fun SelectPaymentMethodPage(){
    val payments = CurrentDataUtils.PaymentsMethod

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.CreditCard,
                contentDescription = "Payments Methods",
                modifier = Modifier.height(18.dp)
            )
            Text(
                text = stringResource(id = R.string.payment_methods),
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(payments) { item ->
                val payment = remember { mutableStateOf(item) }
                PaymentMethodCardReadOnly(payment = payment.value, Modifier
                    .fillMaxWidth()
                    .clickable { CurrentDataUtils.currentPaymentMethodDTO.value = item
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /*MainRouter.changePage(Navigation.PurchasePage)*/ }) {
            Text(text = "Confirm")
        }
    }
}

@Composable
fun ShippingCardReadOnly(address: AddressDTO, modifier: Modifier){
    Card(
        modifier = modifier,
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

@Composable
fun PaymentMethodCardReadOnly(payment: PaymentMethodDTO, modifier: Modifier){
    Card(
        modifier = modifier,
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
                        text = payment.creditCard,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = FontAwesomeIcons.Solid.Calendar,
                    contentDescription = "Phone Number",
                    modifier = Modifier.height(12.dp))
                Text(text = stringResource(id = R.string.expDate) +" ${payment.expiryDate}")

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = FontAwesomeIcons.Solid.PersonBooth,
                    contentDescription = "Owner",
                    modifier = Modifier.height(12.dp))
                Text(text = stringResource(id = R.string.owner) +" ${payment.owner}")

            }
            if(payment.isDefault){
                Text(text = stringResource(id = R.string.defaultPaymentMethod), style = TextStyle(color = DarkGreen, fontWeight = FontWeight.Bold))
            }
        }
    }
}