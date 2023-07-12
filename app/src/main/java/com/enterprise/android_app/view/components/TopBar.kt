package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.CurrentDataUtils.chatUser
import com.enterprise.android_app.model.CurrentDataUtils.inChat
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.Secondary
import com.enterprise.android_app.view.UserPictureAndName
import com.enterprise.android_app.view_models.MessagePageViewModel
import com.enterprise.android_app.view_models.ProfileViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.ExclamationCircle
import compose.icons.fontawesomeicons.solid.Search
import io.swagger.client.models.UserBasicDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch(navController: NavHostController) = TopAppBar(
    title = {
        val searchText = rememberSaveable { mutableStateOf("") }
        TextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                lineHeight = 17.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            },
            leadingIcon = {
                Icon(
                    FontAwesomeIcons.Solid.Search,
                    contentDescription = stringResource(id = R.string.search),
                    modifier = Modifier.size(20.dp))
            },
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                navController.navigate(Navigation.SearchPage.route + "?query=${searchText.value}")
            }),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    },
    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(start = 0.dp, end = 10.dp, top = 10.dp),

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarGeneric(navController: NavHostController) {

    val profileViewModel: ProfileViewModel = viewModel()
    val visitedUser = remember { profileViewModel.visitedUser }
    val currentNavigation = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    val messagePageViewModel: MessagePageViewModel = viewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.Start
        ) {
            TopAppBar(
                title = {
                    if (currentNavigation.value?.destination?.route != Navigation.MessagesPage.route) {
                        when (currentNavigation.value?.destination?.route) {
                            Navigation.HomePage.route -> Text(
                                text = stringResource(id = R.string.app_name),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.SearchPage.route -> Text(
                                text = stringResource(id = R.string.search),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.ProfilePage.route -> Text(
                                text = stringResource(id = R.string.not_my_profile),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.SettingsPage.route -> Text(
                                text = stringResource(id = R.string.settings),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.ProfileDetailsPage.route -> Text(
                                text = stringResource(id = R.string.profile_details),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.AccountSettingsPage.route -> Text(
                                text = stringResource(id = R.string.account_settings),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.ShippingPage.route -> Text(
                                text = stringResource(id = R.string.shipping_page),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.PaymentsPage.route -> Text(
                                text = stringResource(id = R.string.payment_methods),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            Navigation.AboutPage.route -> Text(
                                text = stringResource(id = R.string.about),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )

                            else -> Text(
                                text = stringResource(id = R.string.app_name),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        if (inChat.value) {
                            chatUser.value?.let {
                                UserPictureAndName(user = chatUser.value!!)
                            }

                        } else {
                            Text(
                                text = stringResource(id = R.string.conversations),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            if(inChat.value) {
                                messagePageViewModel.clearCurrentConversation()
                            } else {
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                            contentDescription = stringResource(id = R.string.back),
                            modifier = Modifier.height(20.dp))
                    }
            })
        }

        /*if ((CurrentDataUtils.visitedUser.id != CurrentDataUtils.currentUser?.id &&
            currentNavigation.value?.destination?.route == Navigation.ProfilePage.route) ||
            (currentNavigation.value?.destination?.route == Navigation.MessagesPage.route && inChat.value) ||
                (currentNavigation.value?.destination?.route == Navigation.ProductScreen.route )) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.Start
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.ExclamationCircle,
                        contentDescription = "report",
                        tint = Secondary,
                        modifier = Modifier.height(20.dp)
                    )
                }
            }
        }*/
    }
}

