package com.enterprise.android_app.view.settings.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view.settings.updateUser
import io.swagger.client.models.UserDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailComponent(navController: NavController,user: MutableState<UserDTO?>){
    val modifier = Modifier.fillMaxWidth()
    val emailText: MutableState<String> = remember {
        mutableStateOf(user.value?.email ?: "email not found")
    }
    val emailChangeShow: MutableState<Boolean> = remember { mutableStateOf(false) }
    val currentEmail: MutableState<String> = remember { mutableStateOf(user.value?.email ?: "email not found") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Column(modifier = modifier) {
        Row(modifier = modifier.padding(8.dp)) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email",
                modifier = Modifier.size(24.dp).padding(end = 5.dp)
            )
            Text(text = emailText.value, modifier = modifier.weight(1f))
            if (!emailChangeShow.value)
                TransparentGreenButton(onClick = { emailChangeShow.value = true }, buttonName = "change")
        }

/*                    Row(modifier = modifier.padding(top = 10.dp, start = 10.dp)) {
                Text(text = stringResource(id = R.string.current)+emailText.value, modifier = modifier.weight(1f))
            }*/
        if (emailChangeShow.value){
            val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")

            Row(modifier = modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp).focusRequester(focusRequester)) {
                TextField(
                    value = currentEmail.value,
                    onValueChange = { currentEmail.value = it },
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    textStyle = TextStyle(fontSize = 18.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = !currentEmail.value.matches(emailRegex),
                    label = { if (!currentEmail.value.matches(emailRegex)) Text(stringResource(id = R.string.invalidEmail)) else null }
                )

                IconButton(
                    enabled = (currentEmail.value != emailText.value && currentEmail.value.matches(emailRegex)),
                    onClick = {
                        user.value?.copy(email = currentEmail.value)?.let { updateUser(it) }
                        focusManager.clearFocus()
                        emailChangeShow.value = false
                        emailText.value = currentEmail.value
                        navController.navigate(Navigation.AccountSettingsPage.route)


                    }
                ) {
                    Icon(Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))
                }

            }
        }


    }

}
