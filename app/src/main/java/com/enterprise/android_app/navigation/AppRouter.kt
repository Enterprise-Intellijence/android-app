package com.enterprise.android_app.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen() {
    object SignUpScreen : Screen()
    object LoginScreen : Screen()
    object MainScreen: Screen()

    object StartScreen: Screen()

}

sealed class Navigation() {
    object HomePage : Navigation()

    object SearchPage : Navigation()

    object MessagesPage : Navigation()

    object ProfileMenuPage : Navigation()
    object ProfilePage : Navigation()
    object FavouriteProductScreen : Navigation()
    object SettingsPage : Navigation ()
    object OrdersPage : Navigation()
    object AboutScreen : Navigation()
    object ProductScreen : Navigation()
    object NewProductPage: Navigation()
    object ProfileDetailsScreen : Navigation() {

    }

    object AccountSettingsPage : Navigation() {

    }

    object ShippingScreen : Navigation() {

    }

    object PaymentsScreen : Navigation() {

    }


}



object AppRouter{
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.StartScreen)

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