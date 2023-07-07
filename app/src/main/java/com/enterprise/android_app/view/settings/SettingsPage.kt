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
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.SingleRowTemplate
import com.enterprise.android_app.view_models.UserViewModel
import io.swagger.client.models.UserDTO

@Composable
fun SettingsPage(){
    var modifier = Modifier.fillMaxWidth()
    var user : UserDTO? = CurrentDataUtils.currentUser

    Column(modifier = modifier ) {
        SingleRowTemplate(
            name = "Profile Details",
            icona = Icons.Filled.Person,
            icon_label = stringResource(id = R.string.profile ),
            modifier= modifier,
            onClick = { MainRouter.changePage(Navigation.ProfileDetailsScreen) }
        )
        SingleRowTemplate(
            name = "Account",
            icona = Icons.Filled.List,
            icon_label = stringResource( id = R.string.accountSettings),
            modifier = modifier,
            onClick = { MainRouter.changePage(Navigation.AccountSettingsPage)})
        SingleRowTemplate(
            name = "Shipping",
            icona = Icons.Filled.LocationOn,
            icon_label = stringResource(id = R.string.shipping),
            modifier = modifier, onClick =  { MainRouter.changePage(Navigation.ShippingScreen)})
        SingleRowTemplate(
            name = "Payments",
            icona = Icons.Filled.Notifications,
            icon_label = stringResource(
            id = R.string.payments
        ), modifier = modifier, onClick = { MainRouter.changePage(Navigation.PaymentsScreen)} )
    }
}

fun updateUser(user: UserDTO){
    //val userViewModel : UserViewModel = UserViewModel()
    //userViewModel.saveChange(user)
    //MainRouter.changePage(Navigation.AccountSettingsPage)

}