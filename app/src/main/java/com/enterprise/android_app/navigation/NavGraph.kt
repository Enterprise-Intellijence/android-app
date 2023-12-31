package com.enterprise.android_app.navigation

import EditProductPage
import NewProductPage
import OrdersPage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.enterprise.android_app.view.HomePage
import com.enterprise.android_app.view.LoginPage
import com.enterprise.android_app.view.MainScreen
import com.enterprise.android_app.view.MessagesPage
import com.enterprise.android_app.view.ProfileMenuPage
import com.enterprise.android_app.view.ProfilePage
import com.enterprise.android_app.view.PurchasePage
import com.enterprise.android_app.view.ReportProductPage
import com.enterprise.android_app.view.ReportUserPage
import com.enterprise.android_app.view.SearchPage
import com.enterprise.android_app.view.SelectAddressPage
import com.enterprise.android_app.view.SelectPaymentMethodPage
import com.enterprise.android_app.view.SignUpPage
import com.enterprise.android_app.view.SingleOrderPage
import com.enterprise.android_app.view.screen.FavouriteProductScreen
import com.enterprise.android_app.view.screen.ForgotPasswordScreen
import com.enterprise.android_app.view.screen.ProductScreen
import com.enterprise.android_app.view.screen.StartScreen
import com.enterprise.android_app.view.settings.SettingsPage
import com.enterprise.android_app.view.settings.about.AboutPage
import com.enterprise.android_app.view.settings.account.AccountSettingsPage
import com.enterprise.android_app.view.settings.payments.AddEditPaymentMethodScreen
import com.enterprise.android_app.view.settings.payments.PaymentsPage
import com.enterprise.android_app.view.settings.profiles.ProfileDetailsPage
import com.enterprise.android_app.view.settings.shippings.AddEditShippingScreen
import com.enterprise.android_app.view.settings.shippings.ShippingPage

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.start,
        route = Graph.root
    ) {
        startGraph(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.startGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.StartScreen.route,
        route = Graph.start
    ) {
        composable(Screen.MainScreen.route) { MainScreen() }
        composable(Screen.StartScreen.route) { StartScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginPage(navController) }
        composable(Screen.SignUpScreen.route) { SignUpPage(navController) }
        composable(Screen.ForgotPasswordScreen.route) { ForgotPasswordScreen(navController) }
    }

}

@Composable
@ExperimentalMaterial3Api
fun MainPageGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Navigation.HomePage.route,
        route = Graph.main
    ) {
        composable(Navigation.HomePage.route) { HomePage(navController) }
        composable(Navigation.SearchPage.route + "?query={query}",
            arguments = listOf(navArgument("query") { nullable = true; defaultValue = null })
        ) { backStackEntry ->
            SearchPage(navController, backStackEntry.arguments?.getString("query"))
        }
        composable(Navigation.MessagesPage.route) { MessagesPage(navController) }
        composable(Navigation.ProfileMenuPage.route) { ProfileMenuPage(navController) }
        composable(Navigation.ProfilePage.route + "?visitedUserId={visitedUserId}",
            arguments = listOf(navArgument("visitedUserId") {})
        ) { backStackEntry ->
            ProfilePage(navController, backStackEntry.arguments?.getString("visitedUserId")!!)
        }
        composable(Navigation.FavouriteProductScreen.route) { FavouriteProductScreen(navController) }
        composable(Navigation.SettingsPage.route) { SettingsPage(navController) }
        composable(Navigation.OrdersPage.route) { OrdersPage(navController) }
        composable(
            Navigation.PurchasePage.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") {})
        ) { backStackEntry ->
            PurchasePage(navController, backStackEntry.arguments?.getString("productId")!!)
        }
        composable(Navigation.SelectAddressPage.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") {})
        ) { backStackEntry ->
            SelectAddressPage(navController, backStackEntry.arguments?.getString("productId")!!)
        }
        composable(Navigation.SelectPaymentMethodPage.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") {})
        ) { backStackEntry ->
            SelectPaymentMethodPage(navController, backStackEntry.arguments?.getString("productId")!!)
        }

        composable(Navigation.AboutPage.route) { AboutPage() }
        composable(
            Navigation.ProductScreen.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") {})
        ) { backStackEntry ->
            ProductScreen(navController, backStackEntry.arguments?.getString("productId")!!)
        }
        composable(
            Navigation.EditProductPage.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") {})
        ) { backStackEntry ->
            EditProductPage(navController, backStackEntry.arguments?.getString("productId")!!)
        }
        composable(
            Navigation.NewProductPage.route + "?productId={productId}",
            arguments = listOf(navArgument("productId") { defaultValue = "" })
        ) { backStackEntry ->
            NewProductPage(navController, backStackEntry.arguments?.getString("productId")!!)
        }
        composable(Navigation.AccountSettingsPage.route) { AccountSettingsPage(navController) }
        composable(Navigation.ShippingPage.route) { ShippingPage(navController) }
        composable(Navigation.PaymentsPage.route) { PaymentsPage(navController) }
        // composable(Navigation.ImageSelectorComponent.route) { ImageSelectorComponent(navController) }
        composable(Navigation.ProfileDetailsPage.route) { ProfileDetailsPage(navController) }
        composable(Navigation.AddEditShippingScreen.route) { AddEditShippingScreen(navController) }
        composable(Navigation.AddEditPaymentMethodScreen.route) { AddEditPaymentMethodScreen(navController) }
        composable(
            Navigation.SingleOrderPage.route + "?orderId={orderId}",
            arguments = listOf(navArgument("orderId") {})
        ) { backStackEntry ->
            SingleOrderPage(navController,backStackEntry.arguments?.getString("orderId")!!)
        }
        composable(
            Navigation.ReportUserPage.route + "?reportedUserId={reportedUserId}",
            arguments = listOf(navArgument("reportedUserId") {})
        ) { backStackEntry ->
            ReportUserPage(navController, backStackEntry.arguments?.getString("reportedUserId")!!)
        }
        composable(
            Navigation.ReportProductPage.route + "?reportedProductId={reportedProductId}",
            arguments = listOf(navArgument("reportedProductId") { defaultValue = "" })
        ) { backStackEntry ->
            ReportProductPage(navController, backStackEntry.arguments?.getString("reportedProductId")!!)
        }
    }
}

object Graph {
    const val root = "root_graph"
    const val start = "start_graph"
    const val main = "main_graph"
}
