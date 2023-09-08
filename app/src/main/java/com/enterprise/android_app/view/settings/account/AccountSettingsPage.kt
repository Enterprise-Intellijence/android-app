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
import androidx.navigation.NavController
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view.settings.account.ChangePasswordComponent
import com.enterprise.android_app.view.settings.account.EmailComponent
import com.enterprise.android_app.view.settings.account.UsernameComponent
import io.swagger.client.models.UserDTO

@Composable
fun AccountSettingsPage(navController: NavController){
    Column(modifier = Modifier.padding(bottom =  8.dp)) {
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            EmailComponent(navController)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            UsernameComponent(navController)
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            ChangePasswordComponent(navController)
        }

        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            StatusComponent(navController)
        }


    }

}


