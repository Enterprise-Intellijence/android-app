package com.enterprise.android_app.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


