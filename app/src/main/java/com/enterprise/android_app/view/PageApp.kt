package com.enterprise.android_app.view

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.view.components.TopBarSearch
import com.enterprise.android_app.view.screen.AboutScreen
import com.enterprise.android_app.view.screen.FavouriteProductScreen
import com.enterprise.android_app.view.screen.ProductScreen
import com.enterprise.android_app.view.screen.StartScreen
import io.swagger.client.models.User
import io.swagger.client.models.UserDTO


@Composable
fun PageApp(){
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
                    MainScreen()
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){

    Scaffold(topBar = { SearchTopBar()}, bottomBar = { MainBottomBar()}, floatingActionButton = {}, floatingActionButtonPosition = FabPosition.End) {
        Box(modifier = Modifier.padding(it)){
            when(MainRouter.currentPage.value){
                is Navigation.HomePage ->{
                    HomePage()
                }
                is Navigation.SearchPage ->{
                    SearchPage()
                }
                is Navigation.SellProductPage ->{
                    SellProductPage()
                }
                is Navigation.MessagesPage ->{
                    MessagesPage()
                }
                is Navigation.ProfileMenuPage ->{
                    ProfileMenuPage(UserDTO(null,"Lastfury","email@gmail.com","Bio empty",null ,User.Provider.LOCAL,UserDTO.Status.ACTIVE,null,null,UserDTO.Role.USER,42,23,3,5),R.drawable.foto_profilo4)
                }
                is Navigation.ProfilePage ->{
                    ProfilePage()
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
                is Navigation.AboutScreen ->{
                    AboutScreen()
                }
                is Navigation.ProductScreen ->{
                    ProductScreen()
                }

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
                selected = MainRouter.currentPage.value == Navigation.SellProductPage,
                onClick = { MainRouter.changePage(Navigation.SellProductPage) },
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