package com.enterprise.android_app.view.settings.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
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
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view.settings.updateUser
import io.swagger.client.models.UserDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameComponent(user: MutableState<UserDTO?>){
    val modifier = Modifier.fillMaxWidth()
    val usernameText: MutableState<String> = remember {
        mutableStateOf(user.value?.username ?: "username not found")
    }
    val usernameChangeShow: MutableState<Boolean> = remember { mutableStateOf(false) }
    val currentUsername: MutableState<String> = remember { mutableStateOf(user.value?.username ?: "email not found") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Column(modifier = modifier) {
        Row(modifier = modifier.padding(8.dp)) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Username",
                modifier = Modifier.size(24.dp).padding(end = 5.dp)
            )
            Text(text = usernameText.value, modifier = modifier.weight(1f))
            if(!usernameChangeShow.value)
                TransparentGreenButton(onClick = { usernameChangeShow.value = true }, buttonName = "change")
        }
        if(usernameChangeShow.value){
            Row(modifier = modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp).focusRequester(focusRequester)) {
                TextField(
                    value = currentUsername.value,
                    onValueChange = { currentUsername.value = it },
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    textStyle = TextStyle(fontSize = 18.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                IconButton(
                    enabled = (currentUsername.value != usernameText.value),
                    onClick = {
                        user.value?.copy(username = currentUsername.value)?.let { updateUser(it) }
                        focusManager.clearFocus()
                        usernameChangeShow.value = false
                        usernameText.value = currentUsername.value
                        MainRouter.changePage(Navigation.AccountSettingsPage)

                    }
                ) {
                    Icon(Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))
                }

            }
        }



    }

}
