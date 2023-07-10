package com.enterprise.android_app.view

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.ui.theme.componentShapes
import com.enterprise.android_app.view.components.ButtonComponent
import com.enterprise.android_app.view.components.ClickableLoginTextComponent
import com.enterprise.android_app.view.components.DividerTextComponent
import com.enterprise.android_app.view.components.HeadingTextComponent
import com.enterprise.android_app.view.components.NormalTextComponent
import com.enterprise.android_app.view.components.UnderLinedTextComponent
import com.enterprise.android_app.view_models.AuthViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.EyeSlash


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavHostController) {
    var context = LocalContext.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val authViewModel: AuthViewModel = viewModel()
    var textValueUsername by rememberSaveable{ mutableStateOf("")}
    var textValuePassword by rememberSaveable { mutableStateOf("")}
    val errorMessage = rememberSaveable { mutableStateOf("") }

    val isAuthenticated = rememberSaveable { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            NormalTextComponent(value = stringResource(id = R.string.hello))

            HeadingTextComponent(value = stringResource(id = R.string.welcome_back))

            Spacer(modifier = Modifier.height(20.dp))


            CommonTextFieldEmail(
                title = stringResource(id = R.string.username),
                text = textValueUsername,
                onValueChange = {textValueUsername = it}
            )

            CommonTextFieldPassword(
                title = stringResource(id = R.string.password),
                text = textValuePassword,
                onValueChange = {textValuePassword = it},
                initialPasswordVisible = passwordVisible
            )





            Spacer(modifier = Modifier.height(40.dp))
            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))
            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.button_login),
                onClickAction = {
                    authViewModel.authenticate(
                        "username5",
                        "password5",
                        //textValueUsername.text,
                        //textValuePassword.text,
                        onError = {
                            errorMessage.value =
                                "Authentication failed. Please check your username and password."
                        },
                    onSuccess = { isAuthenticated.value = true}
                    )
                })

            if (isAuthenticated.value) {
                navController.navigate(Screen.MainScreen.route)
            }

            if (errorMessage.value.isNotEmpty()) {
                Text(text = errorMessage.value, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                navController.navigate(Screen.SignUpScreen.route)
            })


        }

    }
    BackHandler() {
        navController.popBackStack()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextFieldEmail(
    title: String,
    text: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = {
                    Text(text = title)
                },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            containerColor = colorResource(id = R.color.colorBackground),
            cursorColor = colorResource(id = R.color.colorPrimary),
            textColor = Color.Black

        ),

        keyboardOptions = KeyboardOptions.Default,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Icon(painterResource(id = R.drawable.message), contentDescription = "")
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextFieldPassword(
    title: String,
    text: String,
    onValueChange: (String) -> Unit,
    initialPasswordVisible: Boolean
){

    var passwordVisible by rememberSaveable { mutableStateOf(initialPasswordVisible) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = title) },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            containerColor = colorResource(id = R.color.colorBackground),
            cursorColor = colorResource(id = R.color.colorPrimary),
            textColor = Color.Black

        ),

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = text,
        onValueChange = {
            onValueChange(it)
        },

        leadingIcon = {
            Icon(painterResource(id = R.drawable.password), contentDescription = "")
        },


        trailingIcon = {
            val iconImage = if (passwordVisible) {
                FontAwesomeIcons.Solid.Eye
            } else {
                FontAwesomeIcons.Solid.EyeSlash
            }

            val description = if (passwordVisible) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    iconImage, contentDescription = description, modifier = Modifier
                        .size(25.dp)
                        .padding(end = 4.dp)
                )
            }
        },

        visualTransformation = if (passwordVisible) VisualTransformation.None else
            PasswordVisualTransformation())
}