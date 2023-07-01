package com.enterprise.android_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Navigation() {
    object HomePage : Navigation() //sostituire con home

    object SearchPage : Navigation()

    object SellProductPage : Navigation()

    object MessagesPage : Navigation()

    object ProfileMenuPage : Navigation()


}

object MainRouter{
    val currentPage : MutableState<Navigation> = mutableStateOf(Navigation.HomePage)

    fun changePage(nextPage: Navigation){
        currentPage.value = nextPage
    }
}