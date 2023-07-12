package com.enterprise.android_app.view

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.CurrentDataUtils.visitedUser
import com.enterprise.android_app.navigation.Graph
import com.enterprise.android_app.navigation.MainPageGraph
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.models.UserDTO

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileMenuPage(navController: NavHostController){
    val modifier = Modifier.fillMaxWidth()
    val user: MutableState<UserDTO?> = remember { mutableStateOf(CurrentDataUtils.currentUser) }

    Column(modifier = modifier ) {
        ClickableBox(
            onClick = {
                navController.navigate(Navigation.ProfilePage.route + "?visitedUserId=${CurrentDataUtils.currentUser?.id}")},
            modifier = modifier,
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(painter = /*radius*/rememberAsyncImagePainter(
                    ImageRequest.Builder(
                        LocalContext.current
                    ).data(data = CurrentDataUtils.currentUser?.photoProfile?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
                        transformations(RoundedCornersTransformation(/*radius*/ 8f))
                    }).build()
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
            onClick = { navController.navigate(Navigation.FavouriteProductScreen.route) }
        )
        SingleRowTemplate(
            name = "Settings",
            icona = Icons.Filled.Settings,
            icon_label = stringResource( id = R.string.settings),
            modifier = modifier,
            onClick = {navController.navigate(Navigation.SettingsPage.route)})
        SingleRowTemplate(name = "My orders", icona = Icons.Filled.Menu, icon_label = stringResource(
            id = R.string.orders
        ), modifier = modifier, onClick =  {navController.navigate(Navigation.OrdersPage.route)})
        SingleRowTemplate(name = "About Svinted", icona = Icons.Filled.Info, icon_label = stringResource(
            id = R.string.about
        ), modifier = modifier, onClick = {navController.navigate(Navigation.AboutPage.route)}
        )
        SingleRowTemplate(name = "Logout", icona = null, icon_label = null, modifier = modifier) {
            CurrentDataUtils.logout()
        }
    }
}

@Composable
fun SingleRowTemplate(
    name: String,
    content: String? = null,
    icona: ImageVector?,
    icon_label: String?,
    modifier: Modifier,
    onClick: ()->Unit
){
    val _content = content ?: ""
    Row(modifier = modifier.height(80.dp)/*.padding(top = 10.dp)*/, verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(80.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(start = 13.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            content = {
                if (icona != null && icon_label != null)
                    Icon(icona, icon_label, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = name ,modifier = modifier.weight(1f), color = MaterialTheme.colorScheme.onSurface,style = TextStyle(fontSize= 18.sp, fontWeight = FontWeight.Normal))
                Spacer(modifier =  Modifier.width(12.dp))
                Text(text = _content, Modifier.widthIn(max = 150.dp),color = Color.Gray,style = TextStyle(fontSize= 18.sp, fontWeight = FontWeight.Light))
                Spacer(modifier = Modifier.width(15.dp))
                Icon(Icons.Filled.KeyboardArrowRight, modifier = Modifier.padding(end = 8.dp),
                    tint = MaterialTheme.colorScheme.onSurface, contentDescription = stringResource(id = R.string.enter) )
            }
        )
    }
    Divider( modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 20.dp), color = Color.Gray )
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