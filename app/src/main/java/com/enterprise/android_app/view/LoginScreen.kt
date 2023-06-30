package com.enterprise.android_app.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.components.ButtonComponent
import com.enterprise.android_app.model.components.ClickableLoginTextComponent
import com.enterprise.android_app.model.components.DividerTextComponent
import com.enterprise.android_app.model.components.HeadingTextComponent
import com.enterprise.android_app.model.components.NormalTextComponent
import com.enterprise.android_app.model.components.TextFieldComponent
import com.enterprise.android_app.model.components.UnderLinedTextComponent
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(){
    val userController: UserControllerApi = UserControllerApi()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello))

            HeadingTextComponent(value = stringResource(id = R.string.welcome_back))

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message))

            TextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.password)
            )

            Spacer(modifier = Modifier.height(40.dp))
            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))
            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.button_login),
                onClickAction = { })
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false ,onTextSelected = {
                    AppRouter.navigateTo(Screen.SignUpScreen)
            })



        }

    }
    BackHandler() {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun PreviewLoginScreen(){
    LoginScreen()
}