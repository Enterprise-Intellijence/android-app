package com.enterprise.android_app.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.ui.theme.Secondary
import com.enterprise.android_app.view.components.ChangePasswordComponent
import com.enterprise.android_app.view.components.EmailComponent
import com.enterprise.android_app.view.components.UsernameComponent
import com.enterprise.android_app.view.screen.ProfileDetailsScreen
import com.enterprise.android_app.view_models.UserViewModel
import io.swagger.client.models.UserDTO

@Composable
fun AccountSettingsPage(){
    var user: MutableState<UserDTO?> = remember {mutableStateOf(CurrentDataUtils.currentUser)}
    Column() {
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            ProfileDetailsScreen(user)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp).background(Secondary)) {
            EmailComponent(user)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            UsernameComponent()
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            ChangePasswordComponent()
        }
        SingleRowTemplate(
            name = "Shipping",
            icona = Icons.Filled.LocationOn,
            icon_label = stringResource(id = R.string.shipping),
            modifier = Modifier, onClick =  { MainRouter.changePage(Navigation.ShippingScreen)})
        SingleRowTemplate(
            name = "Payments",
            icona = Icons.Filled.Notifications,
            icon_label = stringResource(
                id = R.string.payments
            ), modifier = Modifier, onClick = { MainRouter.changePage(Navigation.PaymentsScreen)})
    }

}

fun updateUser(user: UserDTO){
    var userViewModel : UserViewModel = UserViewModel()
    userViewModel.saveChange(user)
    MainRouter.changePage(Navigation.AccountSettingsPage)

}
