package com.enterprise.android_app.view

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen


@Composable
fun PageApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when(currentState.value){
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.LoginScreen -> {
                    LoginScreen()
                }
            }

        }
    }
}