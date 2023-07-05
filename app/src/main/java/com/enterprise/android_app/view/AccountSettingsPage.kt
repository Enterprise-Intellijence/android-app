package com.enterprise.android_app.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
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
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            EmailComponent()
        }
        Row() {
            UsernameComponent()
        }
        Row {
            ChangePasswordComponent()
        }
    }

}

fun updateUser(user: UserDTO){
    var userViewModel : UserViewModel = UserViewModel()
    userViewModel.saveChange(user)
    MainRouter.changePage(Navigation.AccountSettingsPage)

}
