package com.enterprise.android_app.view.screen

import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {

    val success = rememberSaveable { mutableStateOf(false) }

    val email = rememberSaveable { mutableStateOf("") }
    val showToast = remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)) {
        if (success.value) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Transparent), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("Email sent!", fontSize = 22.sp,color = MaterialTheme.colorScheme.onSurface)
            }
        }else{
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp)
                    .background(Color.Transparent), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Enter the email of your account...", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                TextField(value = email.value, onValueChange = { email.value = it }, modifier = Modifier
                    .padding(all = 20.dp)
                    .fillMaxWidth())

                Button(
                    onClick = {
                        resetEmail(email.value, success, showToast)
                    },
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    shape = RoundedCornerShape(7.dp)
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }


    if (showToast.value) {
        showToast.value = false
        Toast.makeText(context, "Something gone wrong, try again", Toast.LENGTH_SHORT).show()
    }


}

fun resetEmail(email: String, success: MutableState<Boolean>, showToast: MutableState<Boolean>) {
    val userControllerApi = UserControllerApi()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            userControllerApi.resetPassword(email)
        }
        catch (e: Exception) {
            showToast.value = true
            return@launch
        }
        success.value = true
    }
}