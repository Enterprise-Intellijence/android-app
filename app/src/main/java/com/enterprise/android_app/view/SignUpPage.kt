package com.enterprise.android_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enterprise.android_app.R
import com.enterprise.android_app.view.components.ButtonComponent
import com.enterprise.android_app.view.components.ClickableLoginTextComponent
import com.enterprise.android_app.view.components.DividerTextComponent
import com.enterprise.android_app.view.components.HeadingTextComponent
import com.enterprise.android_app.view.components.NormalTextComponent
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.ui.theme.componentShapes
import com.enterprise.android_app.view_models.RegistrationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPage(){

    //only for password
    var passwordVisible by rememberSaveable { mutableStateOf(false)}

    //state check
    var textValueUsername by remember { mutableStateOf(TextFieldValue())}
    var textValueEmail by remember { mutableStateOf(TextFieldValue())}
    var textValuePassword by remember { mutableStateOf(TextFieldValue())}

    val registerViewModel : RegistrationViewModel = viewModel()

    val errorMessage = rememberSaveable { mutableStateOf("") }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
        ) {

            NormalTextComponent(value = stringResource(id = R.string.hello))

            HeadingTextComponent(value = stringResource(id = R.string.create_an_account))

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentShapes.small),
                label = { Text(text = stringResource(id = R.string.username)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.colorPrimary),
                    focusedLabelColor =  colorResource(id = R.color.colorPrimary),
                    containerColor = colorResource(id = R.color.colorBackground),
                    cursorColor = colorResource(id = R.color.colorPrimary),
                    textColor = Color.Black

                ),

                keyboardOptions = KeyboardOptions.Default,
                value = textValueUsername,
                onValueChange = {
                    textValueUsername = it
                },
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.profileicon), contentDescription = "")
                }
            )


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentShapes.small),
                label = { Text(text = stringResource(id = R.string.email)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.colorPrimary),
                    focusedLabelColor =  colorResource(id = R.color.colorPrimary),
                    containerColor = colorResource(id = R.color.colorBackground),
                    cursorColor = colorResource(id = R.color.colorPrimary),
                    textColor = Color.Black

                ),

                keyboardOptions = KeyboardOptions.Default,
                value = textValueEmail,
                onValueChange = {
                    textValueEmail = it
                },
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.message), contentDescription = "")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentShapes.small),
                label = { Text(text = stringResource(id = R.string.password)) },

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.colorPrimary),
                    focusedLabelColor =  colorResource(id = R.color.colorPrimary),
                    containerColor = colorResource(id = R.color.colorBackground),
                    cursorColor = colorResource(id = R.color.colorPrimary)

                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = textValuePassword,
                onValueChange = {
                    textValuePassword = it
                },

                leadingIcon = {
                    Icon(painterResource(id = R.drawable.password), contentDescription = "")
                },


                trailingIcon = {
                    val iconImage = if (passwordVisible) {
                        painterResource(id = R.drawable.filled_visibility)
                        //TODO: da trovare un icona migliore
                    } else {
                        painterResource(id = R.drawable.filled_visibility_off)
                        //TODO: da trovare un icona migliore
                    }

                    var description = if (passwordVisible) {
                        stringResource(id = R.string.hide_password)
                    } else {
                        stringResource(id = R.string.show_password)
                    }

                    IconButton(onClick = { passwordVisible= !passwordVisible}) {
                        Icon(painter = iconImage, contentDescription = description)
                    }
                },

                visualTransformation = if(passwordVisible) VisualTransformation.None else
                    PasswordVisualTransformation()
            )


            
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
            {
                if (errorMessage.value.isNotEmpty()) {
                    Text(text = errorMessage.value, color = Color.Red, modifier = Modifier.padding(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            ButtonComponent(value = stringResource(id = R.string.register), onClickAction = {
                registerViewModel.registerUser(
                    textValueUsername.text,
                    textValueEmail.text,
                    textValuePassword.text,
                    errorMessage
                )
            })


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
