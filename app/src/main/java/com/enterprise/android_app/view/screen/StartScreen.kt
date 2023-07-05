package com.enterprise.android_app.view.screen

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
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen

@Composable
fun StartScreen() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Svinted", fontSize = 30.sp)
            

            Button( onClick = { AppRouter.navigateTo(Screen.LoginScreen) },
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(7.dp)
            ) {
                Text(text = stringResource(id = R.string.login))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button( onClick = { AppRouter.navigateTo(Screen.SignUpScreen) },
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

        }
    }
}