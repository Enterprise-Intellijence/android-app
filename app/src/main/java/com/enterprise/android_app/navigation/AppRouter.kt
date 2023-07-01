package com.enterprise.android_app.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen() {
    object SignUpScreen : Screen()
    object LoginScreen : Screen()
    object MainScreen: Screen()

}

sealed class Navigation() {
    object HomePage : Navigation()

    object SearchPage : Navigation()

    object SellProductPage : Navigation()

    object MessagesPage : Navigation()

    object ProfileMenuPage : Navigation()
    object ProfilePage : Navigation()
    object FavouriteProductScreen : Navigation()
    object SettingsPage : Navigation ()


}



object AppRouter{
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}


object MainRouter{
    val currentPage : MutableState<Navigation> = mutableStateOf(Navigation.HomePage)

    fun changePage(nextPage: Navigation){
        currentPage.value = nextPage
    }
}