package com.enterprise.android_app.view.screen

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.view_models.AuthViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import java.lang.Thread.sleep

@Composable
fun StartScreen() {
    val authViewModel: AuthViewModel = viewModel()
    val state = rememberOneTapSignInState()

    OneTapSignInWithGoogle(
        state = state,
        clientId = stringResource(id = R.string.web_client_id),
        onTokenIdReceived = { tokenId ->
            Log.d("LoginPage", "tokenId: $tokenId")
            authViewModel.authenticateGoogle(tokenId) {
                Log.d("LoginPage", "authenticateGoogle error")
            }
        },
        onDialogDismissed = { message ->
            Log.d("LoginPage", "dismissed, message: $message")
        }
    )


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Svinted", fontSize = 30.sp)

            Button( onClick = {

                              AppRouter.navigateTo(Screen.LoginScreen)
                /*
                CurrentDataUtils.checkRefreshToken()
                sleep(3000)
                if(!CurrentDataUtils.hasToCheck)
                    AppRouter.navigateTo(Screen.MainScreen)
                else
                    AppRouter.navigateTo(Screen.LoginScreen)
                */
                              },
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(7.dp)
            ) {
                Text(text = stringResource(id = R.string.login))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { AppRouter.navigateTo(Screen.SignUpScreen) },
                colors = ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(7.dp)
                    ),
                shape = RoundedCornerShape(7.dp)
            ) {
                Text(text = stringResource(id = R.string.sign_up))
            }

            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = { state.open() },
                colors = ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(7.dp)
                    ),
                shape = RoundedCornerShape(7.dp)
            ) {
                Text(text = "continue with Google")
            }


        }
    }
}