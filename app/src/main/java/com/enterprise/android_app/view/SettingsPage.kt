package com.enterprise.android_app.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import io.swagger.client.models.UserDTO

@Composable
fun SettingsPage(user: UserDTO, pic : Int){
    var modifier = Modifier.fillMaxWidth()
    Column(modifier = modifier ) {
        SingleRowTemplate(
            name = "Profile Details",
            icona = Icons.Filled.Person,
            icon_label = stringResource(id = R.string.profile ),
            modifier= modifier,
            onClick = { MainRouter.changePage(Navigation.ProfileDetailsScreen) }
        )
        SingleRowTemplate(
            name = "Account Settings",
            icona = Icons.Filled.List,
            icon_label = stringResource( id = R.string.accountSettings),
            modifier = modifier,
            onClick = { MainRouter.changePage(Navigation.AccountSettingsPage)})
        SingleRowTemplate(
            name = "Shipping",
            icona = Icons.Filled.LocationOn,
            icon_label = stringResource(id = R.string.shipping),
            modifier = modifier, onClick =  { MainRouter.changePage(Navigation.ShippingScreen)})
        SingleRowTemplate(
            name = "Payments",
            icona = Icons.Filled.Notifications,
            icon_label = stringResource(
            id = R.string.payments
        ), modifier = modifier, onClick = { MainRouter.changePage(Navigation.PaymentsScreen)} )
    }
}

