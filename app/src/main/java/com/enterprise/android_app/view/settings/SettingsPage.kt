package com.enterprise.android_app.view.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.SingleRowTemplate
import com.enterprise.android_app.view_models.UserViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CreditCard
import io.swagger.client.models.UserDTO

@Composable
fun SettingsPage(navController: NavController){
    var modifier = Modifier.fillMaxWidth()
    var user : UserDTO? = CurrentDataUtils.currentUser

    Column(modifier = modifier ) {
        SingleRowTemplate(
            name = "Profile Details",
            icona = Icons.Filled.Person,
            icon_label = stringResource(id = R.string.profile ),
            modifier= modifier,
            onClick = { navController.navigate(Navigation.ProfileDetailsPage.route) }
        )
        SingleRowTemplate(
            name = "Account",
            icona = Icons.Filled.List,
            icon_label = stringResource( id = R.string.settings),
            modifier = modifier,
            onClick = { navController.navigate(Navigation.AccountSettingsPage.route)})
        SingleRowTemplate(
            name = "Shipping",
            icona = Icons.Filled.LocationOn,
            icon_label = stringResource(id = R.string.shipping_page),
            modifier = modifier, onClick =  { navController.navigate(Navigation.ShippingPage.route)})
        SingleRowTemplate(
            name = "Payments",
            icona = FontAwesomeIcons.Solid.CreditCard,
            icon_label = stringResource(
            id = R.string.payment_methods
        ), modifier = modifier, onClick = { navController.navigate(Navigation.PaymentsPage.route)} )
    }
}

fun updateUser(user: UserDTO){
    val userViewModel : UserViewModel = UserViewModel()
    userViewModel.saveChange(user)



}

