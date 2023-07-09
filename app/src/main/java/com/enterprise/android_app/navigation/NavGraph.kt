package com.enterprise.android_app.navigation

import NewProductPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.enterprise.android_app.view.HomePage
import com.enterprise.android_app.view.LoginPage
import com.enterprise.android_app.view.MainScreen
import com.enterprise.android_app.view.MessagesPage
import com.enterprise.android_app.view.OrdersPage
import com.enterprise.android_app.view.ProfileMenuPage
import com.enterprise.android_app.view.ProfilePage
import com.enterprise.android_app.view.SearchPage
import com.enterprise.android_app.view.SignUpPage
import com.enterprise.android_app.view.screen.FavouriteProductScreen
import com.enterprise.android_app.view.screen.ProductScreen
import com.enterprise.android_app.view.screen.StartScreen
import com.enterprise.android_app.view.settings.SettingsPage
import com.enterprise.android_app.view.settings.about.AboutPage
import com.enterprise.android_app.view.settings.payments.PaymentsPage
import com.enterprise.android_app.view.settings.profiles.ProfileDetailsPage
import com.enterprise.android_app.view.settings.shippings.AddEditShippingScreen
import com.enterprise.android_app.view.settings.shippings.ShippingPage

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
        composable(Navigation.SearchPage.route) { SearchPage() }
        composable(Navigation.MessagesPage.route) { MessagesPage(navController) }
        composable(Navigation.ProfileMenuPage.route) { ProfileMenuPage(navController) }
        composable(
            Navigation.ProfilePage.route + "?visitedUserId={visitedUserId}",
            arguments = listOf(navArgument("visitedUserId") {})
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("visitedUserId")
                ?.let { ProfilePage(navController, it) }
        }
        composable(Navigation.FavouriteProductScreen.route) { FavouriteProductScreen(navController) }
        composable(Navigation.SettingsPage.route) { SettingsPage(navController) }
        composable(Navigation.OrdersPage.route) { OrdersPage(navController) }
        composable(Navigation.AboutPage.route) { AboutPage()}
        composable(
            Navigation.ProductScreen.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") {})
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("productId")
                ?.let { ProductScreen(navController, it) }
        }
        composable(Navigation.NewProductPage.route) { NewProductPage(navController) }
        composable(Navigation.AccountSettingsPage.route) { AboutPage() }
        composable(Navigation.ShippingPage.route) { ShippingPage(navController) }
        composable(Navigation.PaymentsPage.route) { PaymentsPage(navController) }
        // composable(Navigation.ImageSelectorComponent.route) { ImageSelectorComponent(navController) }
        composable(Navigation.ProfileDetailsPage.route) { ProfileDetailsPage(navController) }
        composable(Navigation.AddEditShippingScreen.route) { AddEditShippingScreen(navController) }
        composable(Navigation.ProfileDetailsPage.route) { AddEditShippingScreen(navController) }



    }
}