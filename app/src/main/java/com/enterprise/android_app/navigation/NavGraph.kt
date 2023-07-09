package com.enterprise.android_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view.HomePage
import com.enterprise.android_app.view.LoginPage
import com.enterprise.android_app.view.MainBottomBar
import com.enterprise.android_app.view.MainScreen
import com.enterprise.android_app.view.SignUpPage
import com.enterprise.android_app.view.screen.StartScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route
    ) {
        composable(Screen.StartScreen.route) { StartScreen(navController) }
        composable(Screen.SignUpScreen.route) { SignUpPage(navController) }
        composable(Screen.LoginScreen.route) { LoginPage(navController) }
        composable(Screen.MainScreen.route) { MainScreen(navController) }
        composable(Navigation.HomePage.route) { HomePage() }
    }
}