package com.enterprise.android_app.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import coil.annotation.ExperimentalCoilApi
import com.enterprise.android_app.model.CurrentDataUtils

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ProfileDetailsScreen(){
    var modifier = Modifier.fillMaxWidth()
    var user : UserDTO? = CurrentDataUtils.currentUser


    Column(modifier = modifier ) {
        ClickableBox(
            onClick = { /*MainRouter.changePage(Navigation.ProfilePage)*/ },
            modifier = modifier,
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(painter = rememberImagePainter(
                    data = user?.photoProfile?.urlPhoto,
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
        val textState = remember { mutableStateOf(TextFieldValue(user?.bio ?:"")) }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Bio",
                fontSize = 20.sp,
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)

            )
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
    }
}




