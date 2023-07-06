package com.enterprise.android_app.view.settings.account


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
import com.enterprise.android_app.view.settings.account.ChangePasswordComponent
import com.enterprise.android_app.view.settings.account.EmailComponent
import com.enterprise.android_app.view.settings.account.UsernameComponent
import io.swagger.client.models.UserDTO

@Composable
fun AccountSettingsPage(){
    var user: MutableState<UserDTO?> = remember {mutableStateOf(CurrentDataUtils.currentUser)}
    Column(modifier = Modifier.padding(bottom =  8.dp)) {
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            EmailComponent(user)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            UsernameComponent(user)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            ChangePasswordComponent(user)
        }

    }

}


