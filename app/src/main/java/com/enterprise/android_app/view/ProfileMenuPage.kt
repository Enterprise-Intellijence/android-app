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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.CurrentDataUtils.visitedUser
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserDTO

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileMenuPage(){
    val modifier = Modifier.fillMaxWidth()
    val user: MutableState<UserDTO?> = remember { mutableStateOf(CurrentDataUtils.currentUser) }

    Column(modifier = modifier ) {
        ClickableBox(
            onClick = {
                visitedUser = CurrentDataUtils.toUserBasicDTO(CurrentDataUtils.currentUser!!)
                MainRouter.changePage(Navigation.ProfilePage)},
            modifier = modifier,
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(painter = rememberImagePainter(
                    data = user.value?.photoProfile?.urlPhoto,
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
                        Text(text = user.value?.username ?:"no username", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
                        Text(text = stringResource(id = R.string.viewMyProfile), Modifier.padding(top = 20.dp))
                    }
                    Icon(Icons.Filled.KeyboardArrowRight,contentDescription = stringResource (R.string.viewMyProfile))

                }
            }
        }
        SingleRowTemplate(name = "Favourite Items", icona = Icons.Filled.Favorite, icon_label = stringResource(id = R.string.favourite ), modifier= modifier,
            onClick = { MainRouter.changePage(Navigation.FavouriteProductScreen) }
        )
        SingleRowTemplate(
            name = "Account Settings",
            icona = Icons.Filled.Settings,
            icon_label = stringResource( id = R.string.settings),
            modifier = modifier,
            onClick = {MainRouter.changePage(Navigation.AccountSettingsPage)})
        SingleRowTemplate(name = "My orders", icona = Icons.Filled.Menu, icon_label = stringResource(
            id = R.string.watch_orders
        ), modifier = modifier, onClick =  {MainRouter.changePage(Navigation.OrdersPage)})
        SingleRowTemplate(name = "About Svinted", icona = Icons.Filled.Info, icon_label = stringResource(
            id = R.string.aboutSvinted
        ), modifier = modifier, onClick = {MainRouter.changePage(Navigation.AboutScreen)} )
    }
}

@Composable
fun SingleRowTemplate(
    name: String,
    icona: ImageVector,
    icon_label: String,
    modifier: Modifier,
    onClick: ()->Unit
){
    Row(modifier = modifier.height(80.dp)/*.padding(top = 10.dp)*/, verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(80.dp),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RectangleShape,
            contentPadding = PaddingValues(start = 8.dp),
            content = {
                Icon(icona, icon_label, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = name,modifier = modifier.weight(1f), style = TextStyle(fontSize= 18.sp, fontWeight = FontWeight.Light))
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(id = R.string.enter))
            }
        )
    }
}

@Composable
fun ClickableBox(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .height(120.dp),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}