package com.enterprise.android_app.view

import NewProductPage
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.enterprise.android_app.MainActivity
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.view.components.TopBarGeneric
import com.enterprise.android_app.view.components.TopBarSearch
import com.enterprise.android_app.view.screen.FavouriteProductScreen
import com.enterprise.android_app.view.settings.payments.PaymentsPage
import com.enterprise.android_app.view.screen.ProductScreen
import com.enterprise.android_app.view.settings.profiles.ProfileDetailsPage
import com.enterprise.android_app.view.settings.shippings.ShippingPage
import com.enterprise.android_app.view.settings.SettingsPage
import com.enterprise.android_app.view.settings.about.AboutPage
import com.enterprise.android_app.view.settings.account.AccountSettingsPage
import com.enterprise.android_app.view.settings.payments.AddEditPaymentMethodScreen
import com.enterprise.android_app.view.settings.shippings.AddEditShippingScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Envelope
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.PlusCircle
import compose.icons.fontawesomeicons.solid.Search
import compose.icons.fontawesomeicons.solid.User
import java.io.File


@Composable
fun PageApp(mainActivity: MainActivity) {

    LaunchedEffect(key1 = "PageApp", block = {
        //CurrentDataUtils.checkRefreshToken()
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when(currentState.value){

                //TODO Implementare il tasto indietro che ritorna alla pagina precedente
                is Screen.StartScreen -> {

                    //StartScreen()
                    //ProductScreen()
                }
                is Screen.SignUpScreen -> {
                    //SignUpPage()
                    //ProductScreen()
                    //MainScreen()
                }
                is Screen.LoginScreen -> {
                    //LoginPage()
                }
                is Screen.MainScreen -> {
                    //MainScreen()
                }

                else -> {}
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val currentNavigation = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    Scaffold(
        topBar = { SearchTopBar(navController)},
        bottomBar = { MainBottomBar(navController)},
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(modifier = Modifier.padding(it)) {
                when(currentNavigation.value?.destination?.route){
                    Navigation.HomePage.route -> { HomePage() }
                    Navigation.SearchPage.route -> { SearchPage() }
                    Navigation.MessagesPage.route -> { MessagesPage() }
                    Navigation.ProfileMenuPage.route -> { ProfileMenuPage() }
                    Navigation.ProfilePage.route -> { ProfilePage(CurrentDataUtils.visitedUser) }
                    Navigation.FavouriteProductScreen.route -> { FavouriteProductScreen() }
                    Navigation.SettingsPage.route -> { SettingsPage() }
                    Navigation.OrdersPage.route ->{ OrdersPage() }
                    Navigation.AboutPage.route ->{ AboutPage() }
                    Navigation.ProductScreen.route ->{ ProductScreen( CurrentDataUtils.currentProductId) }
                    Navigation.NewProductPage.route ->{ NewProductPage() }
                    Navigation.AccountSettingsPage.route ->{ AccountSettingsPage() }
                    Navigation.ShippingPage.route ->{ ShippingPage() }
                    Navigation.PaymentsPage.route ->{ PaymentsPage() }
                    Navigation.ProfileDetailsPage.route ->{ ProfileDetailsPage() }
                    Navigation.AddEditShippingScreen.route ->{ AddEditShippingScreen() }
                    Navigation.PaymentsPage.route ->{ PaymentsPage() }
                    Navigation.AddEditPaymentMethodScreen.route ->{ AddEditPaymentMethodScreen() }
                    /*is Navigation.ImageSelectorComponent ->{
                        is Navigation.ImageSelectorComponent ->{
                    is Navigation.ImageSelectorComponent ->{
                        ImageSelectorComponent(
                            fileState = fileState,
                            onFileUploaded = {
                                //MainRouter.changePage(Navigation.ProfileDetailsScreen)
                            })
                    }*/
                    else -> {}
                    }

                BackHandler {
                    navController.popBackStack()
                }
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(navController: NavHostController) {

    if (MainRouter.currentPage.value == Navigation.HomePage || MainRouter.currentPage.value == Navigation.SearchPage) {
        TopBarSearch()
    } else {
        TopBarGeneric(navController)
    }


}

@Composable
fun MainBottomBar(navController: NavHostController) {

    val currentNavigation = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    BottomAppBar() {
        NavigationBar() {
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.HomePage.route,
                onClick = { navController.navigate(Navigation.HomePage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.Home,
                        contentDescription = stringResource(id = R.string.home))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.SearchPage.route,
                onClick = { navController.navigate(Navigation.SearchPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.Search,
                        contentDescription = stringResource(id = R.string.search))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.NewProductPage.route,
                onClick = { navController.navigate(Navigation.SearchPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.PlusCircle,
                        contentDescription = stringResource(id = R.string.sell))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.MessagesPage.route,
                onClick = { navController.navigate(Navigation.MessagesPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.Envelope,
                        contentDescription = stringResource(id = R.string.inbox))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.ProfileMenuPage.route,
                onClick = { navController.navigate(Navigation.ProfilePage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.User,
                        contentDescription = stringResource(id = R.string.profile))
                }
            )
        }
    }
}