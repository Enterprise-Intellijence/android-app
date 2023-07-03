package com.enterprise.android_app.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.ClickableBox
import com.enterprise.android_app.view.SingleRowTemplate
import io.swagger.client.models.UserDTO

@Composable
fun ProfileDetailsScreen(user: UserDTO){
    var modifier = Modifier.fillMaxWidth()

    Column(modifier = modifier ) {
        ClickableBox(
            onClick = { /*MainRouter.changePage(Navigation.ProfilePage)*/ },
            modifier = modifier,
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(painter = rememberImagePainter(
                    data = user.photoProfile?.urlPhoto,
                    builder = {
                        transformations(RoundedCornersTransformation(/*radius*/ 8f))
                    }
                ),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .padding(0.dp)
                )
                Row() {
                    Column(modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp))
                    {
                        Text(text = stringResource(id = R.string.changePhoto), style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
                    }
                    Icon(Icons.Filled.KeyboardArrowRight,contentDescription = stringResource (R.string.changePhoto))

                }
            }
        }
/*        SingleRowTemplate(name = "Favourite Items", icona = Icons.Filled.Favorite, icon_label = stringResource(id = R.string.favourite ), modifier= modifier,
            onClick = { MainRouter.changePage(Navigation.FavouriteProductScreen) }
        )
        SingleRowTemplate(
            name = "Settings",
            icona = Icons.Filled.Settings,
            icon_label = stringResource( id = R.string.settings),
            modifier = modifier,
            onClick = { MainRouter.changePage(Navigation.SettingsPage)})
        SingleRowTemplate(name = "My orders", icona = Icons.Filled.Menu, icon_label = stringResource(
            id = R.string.watch_orders
        ), modifier = modifier, onClick =  { MainRouter.changePage(Navigation.OrdersPage)})
        SingleRowTemplate(name = "About Svinted", icona = Icons.Filled.Info, icon_label = stringResource(
            id = R.string.aboutSvinted
        ), modifier = modifier, onClick = { MainRouter.changePage(Navigation.AboutScreen)} )*/
    }
}

