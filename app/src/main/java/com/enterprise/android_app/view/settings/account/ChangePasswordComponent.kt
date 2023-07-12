package com.enterprise.android_app.view.settings.account

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view.settings.profiles.mToast
import com.enterprise.android_app.view_models.UserViewModel
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.UserDTO


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordComponent(navController: NavController){
    val userViewModel = UserViewModel()

    val context = LocalContext.current

    var user: MutableState<UserDTO?> = remember {mutableStateOf(CurrentDataUtils.currentUser)}

    val modifier = Modifier.fillMaxWidth()
    val oldPassword: MutableState<String> = rememberSaveable {mutableStateOf("")}
    val newPassword: MutableState<String> = rememberSaveable {mutableStateOf("")}
    val repNewPassword: MutableState<String> = rememberSaveable { mutableStateOf("")}
    val passChangeShow: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val updated = userViewModel.updated
    val logoutRequired = userViewModel.logoutRequired

    if(logoutRequired.value){
        logoutRequired.value = false
        if(updated.value) {
            CurrentDataUtils.logout()
            val packageManager: PackageManager = context.packageManager
            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
            val componentName: ComponentName = intent.component!!
            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
            context.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
        }
    }

    Column(modifier = modifier) {
        Row(modifier = modifier.padding(8.dp)) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 5.dp)
            )
            Text(text = stringResource(id = R.string.changePassword), modifier = modifier.weight(1f))
            if (!passChangeShow.value)
                TransparentGreenButton(onClick = { passChangeShow.value = true }, buttonName = "change")
        }

        if (passChangeShow.value){
            Column(modifier = modifier.padding(start = 10.dp, end = 20.dp)) {
                Text(text = stringResource(id = R.string.oldPassword))
                Row(modifier = modifier
                    .focusRequester(focusRequester)
                    .padding(bottom = 10.dp)) {
                    TextField(
                        value = oldPassword.value,
                        onValueChange = { oldPassword.value = it },
                        //label = { Text(text = stringResource(id = R.string.oldPassword))},
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textStyle = TextStyle(fontSize = 18.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        visualTransformation = PasswordVisualTransformation(),
                    )
                }
                Text(text = stringResource(id = R.string.newPassword))
                Row(modifier = modifier
                    .focusRequester(focusRequester)
                    .padding(bottom = 10.dp)) {
                    TextField(
                        value = newPassword.value,
                        onValueChange = { newPassword.value = it },
                        //label = { Text(text = stringResource(id = R.string.newPassword))},
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textStyle = TextStyle(fontSize = 18.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        visualTransformation = PasswordVisualTransformation(),
                        isError = newPassword.value.length<8 ,
                        label = {if(newPassword.value.isNotEmpty() && newPassword.value.length<8) Text(text = stringResource(id = R.string.passwordTooShort))}
                    )
                }
                Text(text = stringResource(id = R.string.repeatNewPassword))

                Row(modifier = modifier
                    .focusRequester(focusRequester)
                    .padding(bottom = 10.dp)) {
                    TextField(
                        value = repNewPassword.value,
                        onValueChange = { repNewPassword.value = it },
                        //label = { Text(text = stringResource(id = R.string.repeatNewPassword))},
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textStyle = TextStyle(fontSize = 18.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        visualTransformation = PasswordVisualTransformation(),
                        isError = repNewPassword.value.length<8 || repNewPassword.value!=newPassword.value,
                        label = {if(repNewPassword.value.isNotEmpty() && newPassword.value.length>=8 && repNewPassword.value.length<8) Text(text = stringResource(id = R.string.passwordTooShort)) else if (repNewPassword.value!=newPassword.value) Text(
                            text = stringResource(id = R.string.passwordMissmatch)) else null
                         }

                    )
                }
                IconButton(
                    enabled = (newPassword.value != "" && newPassword.value != oldPassword.value && newPassword.value == repNewPassword.value),
                    onClick = {
                        userViewModel.changePassword(oldPassword = oldPassword.value, newPassword = newPassword.value)
                        focusManager.clearFocus()
                        passChangeShow.value = false





                    },
                    modifier = Modifier.align(Alignment.End)

                ) {
                    Icon( Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))
                }




            }
        }
    }


}

