package com.enterprise.android_app.view.screen

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.MainActivity
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
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import java.lang.Thread.sleep

@Composable
fun StartScreen(navController: NavHostController) {
    val authViewModel: AuthViewModel = viewModel()
    val state = rememberOneTapSignInState()

    LaunchedEffect(key1 = "StartScreen", block = {
        CurrentDataUtils.checkRefreshToken()
    })

    if (CurrentDataUtils.goToHome.value) {
        CurrentDataUtils.goToHome.value = false
        navController.navigate(Screen.MainScreen.route)
    }


    OneTapSignInWithGoogle(
        state = state,
        clientId = stringResource(id = R.string.web_client_id),
        onTokenIdReceived = { tokenId ->
            Log.d("LoginPage", "tokenId: $tokenId")
            authViewModel.authenticateGoogle(tokenId, onError = {
            }, onSuccess = {
                CurrentDataUtils.goToHome.value = true
            })
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

            Text(text = "Svinted", fontSize = 30.sp, color = MaterialTheme.colorScheme.primary)
            if (CurrentDataUtils.showLoadingScreen.value) {
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator(Modifier.size(40.dp))
            }else {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { navController.navigate(Screen.LoginScreen.route) },

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
                    onClick = { navController.navigate(Screen.SignUpScreen.route) },
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
                    Text(text = "Continue with Google")
                }
            }

        }
    }
}