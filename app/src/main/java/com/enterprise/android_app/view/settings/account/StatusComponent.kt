package com.enterprise.android_app.view.settings.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view_models.UserViewModel
import io.swagger.client.models.UserDTO


@Composable
fun StatusComponent(navController: NavController){
    val userViewModel = UserViewModel()
    var user: MutableState<UserDTO?> = remember { mutableStateOf(CurrentDataUtils.currentUser) }

    val onHoliday: MutableState<String> = rememberSaveable { mutableStateOf("ACTIVE") }

    val modifier = Modifier.fillMaxWidth()

    Column(modifier = modifier) {
        Row(modifier = modifier.padding(8.dp)){
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Status",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 5.dp)
            )
            Text("Status")
            Button(onClick = {
                onHoliday.value = "HOLIDAY"
                user.value?.let { userViewModel.changeStatus(it.id,onHoliday.value) }

            }) {

            }

        }
    }

}