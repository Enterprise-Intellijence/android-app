package com.enterprise.android_app.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.MainPageGraph
import com.enterprise.android_app.navigation.NavGraph
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.view.components.TopBarGeneric
import com.enterprise.android_app.view.components.TopBarSearch
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Envelope
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.PlusCircle
import compose.icons.fontawesomeicons.solid.Search
import compose.icons.fontawesomeicons.solid.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = { SearchTopBar(navController)},
        bottomBar = { MainBottomBar(navController) },
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(modifier = Modifier.padding(it)) {

                MainPageGraph(navController = navController)

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

    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    if (currentRoute.value?.destination?.route == Navigation.HomePage.route ||
        (currentRoute.value?.destination?.route != null &&
                currentRoute.value?.destination?.route?.contains(Navigation.SearchPage.route)!!)) {
        TopBarSearch(navController)
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
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(id = R.string.home))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route != null &&
                        currentNavigation.value?.destination?.route?.contains(Navigation.SearchPage.route)!!,
                onClick = { navController.navigate(Navigation.SearchPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.Search,
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(id = R.string.search))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.NewProductPage.route,
                onClick = { navController.navigate(Navigation.NewProductPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.PlusCircle,
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(id = R.string.sell))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.MessagesPage.route,
                onClick = { navController.navigate(Navigation.MessagesPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.Envelope,
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(id = R.string.inbox))}
            )
            NavigationBarItem(
                selected = currentNavigation.value?.destination?.route == Navigation.ProfileMenuPage.route,
                onClick = { navController.navigate(Navigation.ProfileMenuPage.route) },
                icon = {
                    Icon(
                        FontAwesomeIcons.Solid.User,
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(id = R.string.profile))
                }
            )
        }
    }
}