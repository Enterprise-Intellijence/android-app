package com.enterprise.android_app.view

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
import com.enterprise.android_app.view.components.ButtonComponent
import com.enterprise.android_app.view.components.ClickableLoginTextComponent
import com.enterprise.android_app.view.components.DividerTextComponent
import com.enterprise.android_app.view.components.HeadingTextComponent
import com.enterprise.android_app.view.components.NormalTextComponent
import com.enterprise.android_app.view.components.PasswordTextFieldComponent
import com.enterprise.android_app.view.components.TextFieldComponent
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen


@Composable
fun SignUpPage(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(28.dp)) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_an_account))
            Spacer(modifier = Modifier.height(20.dp))
            /*
            TextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profileicon)

            )
            TextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profileicon)
            )
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message)
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.password)
            )
            */
            
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(value = stringResource(id = R.string.register), onClickAction = {})

            Spacer(modifier = Modifier.height(15.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true,onTextSelected = {
                    AppRouter.navigateTo(Screen.LoginScreen)
            })
        }
        
        

    }

}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpPage()
}
