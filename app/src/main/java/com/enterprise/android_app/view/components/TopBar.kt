package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.Secondary
import com.enterprise.android_app.view_models.ProfileViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.ExclamationCircle
import io.swagger.client.models.UserBasicDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch() = TopAppBar(
    title = {
        TextField(
            value = "",
            onValueChange = { /*TODO: Gestisci il valore del TextField*/ },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.search))
            },
            singleLine = true,
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
fun TopBarGeneric() {

    val profileViewModel: ProfileViewModel = ProfileViewModel()
    val visitedUser = remember { profileViewModel.visitedUser }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            horizontalAlignment = Alignment.Start
        ) {
            TopAppBar(
                title = {
                    when (MainRouter.currentPage.value) {
                        Navigation.HomePage -> Text(
                            text = stringResource(id = R.string.app_name),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )

                        Navigation.SearchPage -> Text(
                            text = stringResource(id = R.string.search),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )
                        Navigation.ProfilePage -> Text(
                                text = stringResource(id = R.string.not_my_profile),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                        )

                        Navigation.SettingsPage -> Text(
                            text = stringResource(id = R.string.settings),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )

                        Navigation.ProfileDetailsPage -> Text(
                            text = stringResource(id = R.string.profile_details),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )

                        Navigation.AccountSettingsPage -> Text(
                            text = stringResource(id = R.string.account_settings),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )

                        Navigation.ShippingPage -> Text(
                            text = stringResource(id = R.string.shipping_page),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )

                        Navigation.PaymentsPage -> Text(
                            text = stringResource(id = R.string.payment_methods),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )

                        Navigation.AboutPage -> Text(
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
                },

                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                            contentDescription = stringResource(id = R.string.back),
                            modifier = Modifier.height(20.dp)
                        )
                    }
                },
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            if (MainRouter.currentPage.value == Navigation.ProfilePage) {
                if (visitedUser.value != null) {
                    if (CurrentDataUtils.currentUser?.id != profileViewModel.visitedUser.value?.id) {
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
                }
            }
        }

    }
}
