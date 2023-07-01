package com.enterprise.android_app.view

import android.provider.ContactsContract.Profile
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
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
                    //SignUpScreen()
                    MainScreen()
                }
                is Screen.LoginScreen -> {
                    LoginScreen()
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
/*    val navigation: Navigation*/
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
                    ProfileMenuPage()
                }
            }

        }
        
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name))},
        navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = stringResource(id = R.string.back))
        } },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Settings,contentDescription = stringResource(id = R.string.settings))

            }
        }

        )
}

@Composable
fun MainBottomBar(){
    var selectedIndex = remember { mutableStateOf(0)}
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
                onClick = { MainRouter.changePage(Navigation.SearchPage) },
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