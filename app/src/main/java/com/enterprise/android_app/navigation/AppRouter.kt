package com.enterprise.android_app.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route: String) {
    object PageApp : Screen(route = "page_app")
    object SignUpScreen : Screen(route = "sign_up_screen")
    object LoginScreen : Screen(route = "login_screen")
    object MainScreen: Screen(route = "main_screen")
    object StartScreen: Screen(route = "start_screen")

}

sealed class Navigation(val route: String) {
    object HomePage : Navigation(route = "home_page")
    object SearchPage : Navigation(route = "search_page")
    object MessagesPage : Navigation(route = "messages_page")
    object ProfileMenuPage : Navigation(route = "profile_menu_page")
    object ProfilePage : Navigation(route = "profile_page")
    object FavouriteProductScreen : Navigation(route = "favourite_product_screen")
    object SettingsPage : Navigation (route = "settings_page")
    object OrdersPage : Navigation(route = "orders_page")
    object AboutPage : Navigation(route = "about_page")
    object ProductScreen : Navigation(route = "product_screen")
    object NewProductPage: Navigation(route = "new_product_page")
    object EditProductPage: Navigation(route = "edit_product_page")
    object AccountSettingsPage : Navigation(route = "account_settings_page")
    object ShippingPage : Navigation(route = "shipping_page")
    object PaymentsPage : Navigation(route = "payments_page")
    object PurchasePage : Navigation(route = "purchase_page")
    object SelectAddressPage : Navigation(route = "select_address_page")
    object SelectPaymentMethodPage : Navigation(route = "select_payment_method_page")
    object ImageSelectorComponent: Navigation(route = "image_selector_component")
    object ProfileDetailsPage : Navigation(route = "profile_details_page")
    object AddEditShippingScreen : Navigation(route = "add_edit_shipping_screen")
    object AddEditPaymentMethodScreen : Navigation(route = "add_edit_payment_method_screen")
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