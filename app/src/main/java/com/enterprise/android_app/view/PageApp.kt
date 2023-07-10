package com.enterprise.android_app.view

import NewProductPage
import OrdersPage
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.enterprise.android_app.view.screen.StartScreen
import com.enterprise.android_app.view.settings.SettingsPage
import com.enterprise.android_app.view.settings.about.AboutPage
import com.enterprise.android_app.view.settings.account.AccountSettingsPage
import com.enterprise.android_app.view.settings.payments.AddEditPaymentMethodScreen
import com.enterprise.android_app.view.settings.shippings.AddEditShippingScreen
import java.io.File


@Composable
fun PageApp(mainActivity: MainActivity) {

    LaunchedEffect(key1 = "PageApp", block = {
        CurrentDataUtils.checkRefreshToken()
    })
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when(currentState.value){

                //TODO Implementare il tasto indietro che ritorna alla pagina precedente
                is Screen.StartScreen -> {
                    StartScreen()
                    //ProductScreen()
                }
                is Screen.SignUpScreen -> {
                    SignUpPage()
                    //ProductScreen()
                    //MainScreen()
                }
                is Screen.LoginScreen -> {
                    LoginPage()
                }
                is Screen.MainScreen -> {
                    MainScreen(mainActivity)
                }

                else -> {}
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainActivity: MainActivity) {
    val fileState = remember { mutableStateOf<File?>(null) }



    Scaffold(topBar = { SearchTopBar()}, bottomBar = { MainBottomBar()}, floatingActionButton = {}, floatingActionButtonPosition = FabPosition.End) {
        Box(modifier = Modifier.padding(it)) {
            when(MainRouter.currentPage.value){
                is Navigation.HomePage ->{
                    HomePage()
                }
                is Navigation.SearchPage ->{
                    SearchPage()
                }
                is Navigation.MessagesPage ->{
                    MessagesPage()
                }
                is Navigation.ProfileMenuPage ->{
                    ProfileMenuPage()
                }
                is Navigation.ProfilePage ->{
                    ProfilePage(CurrentDataUtils.visitedUser)
                }
                is Navigation.FavouriteProductScreen ->{
                    FavouriteProductScreen()
                }
                is Navigation.SettingsPage ->{
                    SettingsPage()
                }
                is Navigation.OrdersPage ->{
                    OrdersPage()
                }
                is Navigation.PurchasePage ->{
                    PurchasePage()
                }
                is Navigation.SelectAddressPage ->{
                    SelectAddressPage()
                }
                is Navigation.SelectPaymentPage ->{
                    SelectPaymentMethodPage()
                }

                is Navigation.AboutPage ->{
                    AboutPage()
                }
                is Navigation.ProductScreen ->{
                    ProductScreen( CurrentDataUtils.currentProductId)
                }
                is Navigation.NewProductPage ->{
                    NewProductPage()
                }
                is Navigation.AccountSettingsPage ->{
                    AccountSettingsPage()
                }
                is Navigation.ShippingPage ->{
                    ShippingPage()
                }
                is Navigation.ProfileDetailsPage ->{
                    ProfileDetailsPage()
                }
                is Navigation.AddEditShippingScreen ->{
                   AddEditShippingScreen()

                }
                is Navigation.PaymentsPage ->{
                    PaymentsPage()
                }
                is Navigation.AddEditPaymentMethodScreen ->{
                    AddEditPaymentMethodScreen(mainActivity)
                }
                //is Navigation.ImageSelectorComponent ->{
                /*is Navigation.ImageSelectorComponent ->{
                is Navigation.ImageSelectorComponent ->{
                    ImageSelectorComponent(
                        fileState = fileState,
                        onFileUploaded = {
                            //MainRouter.changePage(Navigation.ProfileDetailsScreen)
                        })
                }*/

                else -> {}
            }

        }
        
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(){

    if (MainRouter.currentPage.value == Navigation.HomePage || MainRouter.currentPage.value == Navigation.SearchPage)
    {
       TopBarSearch()
    }
    else
        TopBarGeneric()


}

@Composable
fun MainBottomBar(){

    BottomAppBar() {
        NavigationBar() {
            NavigationBarItem(
                selected = MainRouter.currentPage.value == Navigation.HomePage,
                onClick = { MainRouter.changePage(Navigation.HomePage)  },
                icon = {Icon(Icons.Filled.Home, contentDescription = stringResource(id = R.string.home))}
            )
            NavigationBarItem(
                selected = MainRouter.currentPage.value == Navigation.SearchPage,
                onClick = { MainRouter.changePage(Navigation.SearchPage) },
                icon = {Icon(Icons.Filled.Search, contentDescription = stringResource(id = R.string.search))}
            )
            NavigationBarItem(
                selected = MainRouter.currentPage.value == Navigation.NewProductPage,
                onClick = { MainRouter.changePage(Navigation.NewProductPage) },
                icon = {Icon(Icons.Filled.AddCircle, contentDescription = stringResource(id = R.string.sell))}
            )
            NavigationBarItem(
                selected = MainRouter.currentPage.value == Navigation.MessagesPage,
                onClick = { MainRouter.changePage(Navigation.MessagesPage) },
                icon = {Icon(Icons.Filled.Email, contentDescription = stringResource(id = R.string.inbox))}
            )
            NavigationBarItem(
                selected = MainRouter.currentPage.value == Navigation.ProfileMenuPage,
                onClick = { MainRouter.changePage(Navigation.ProfileMenuPage) },
                icon = {Icon(Icons.Filled.Person, contentDescription = stringResource(id = R.string.profile))}
            )

        }
        
    }

}