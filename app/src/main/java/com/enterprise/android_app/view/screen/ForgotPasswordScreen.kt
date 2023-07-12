package com.enterprise.android_app.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {


    val email = rememberSaveable { mutableStateOf("") }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = email.value, onValueChange = { email.value = it }, modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxWidth(), placeholder = { "Email..."})

        Button(onClick = { resetEmail(email.value) }) {
            Text("Submit", color = MaterialTheme.colorScheme.onSurface)
        }
    }

}

fun resetEmail(email: String) {
    CoroutineScope(Dispatchers.IO).launch {
        val userControllerApi = UserControllerApi()
        userControllerApi.resetPassword(email)
    }
}