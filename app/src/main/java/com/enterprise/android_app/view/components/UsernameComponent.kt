package com.enterprise.android_app.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.Secondary
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import io.swagger.client.models.UserDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameComponent(){
    val modifier = Modifier.fillMaxWidth()
    val user: UserDTO? = CurrentDataUtils.currentUser
    val usernameText: MutableState<String> = remember {
        mutableStateOf(CurrentDataUtils.currentUser?.username ?: "username not found")
    }
    val usernameChangeShow: MutableState<Boolean> = remember { mutableStateOf(false) }
    val currentUsername: MutableState<String> = remember { mutableStateOf(CurrentDataUtils.currentUser?.username ?: "email not found") }

    Column(modifier = modifier) {
        Row(modifier = modifier.background(color = Secondary)) {
            Column(modifier = modifier) {
                if (!usernameChangeShow.value) {
                    Row(modifier = modifier.padding(8.dp)) {
                        Text(text = usernameText.value, modifier = modifier.weight(1f))
                        TransparentGreenButton(onClick = { usernameChangeShow.value = true }, buttonName = "change")
                    }
                } else {
                    Row(modifier = modifier.padding(top = 10.dp, start = 10.dp)) {
                        Text(text = stringResource(id = R.string.current) +usernameText.value, modifier = modifier.weight(1f))
                    }
                    Row(modifier = modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp)) {
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
                                /*user?.let { currentUser ->
                                    save(currentUser, currentTextState)
                                }*/
                            }
                        ) {
                            Icon(Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))
                        }

                    }
                }
            }

        }
    }

}