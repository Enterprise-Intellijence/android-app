package com.enterprise.android_app.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.enterprise.android_app.view.ClickableBox
import io.swagger.client.models.UserDTO
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import coil.annotation.ExperimentalCoilApi
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view_models.UserViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ProfileDetailsScreen(){
    var modifier = Modifier.fillMaxWidth()
    var user : UserDTO? = CurrentDataUtils.currentUser


    Column(modifier = modifier ) {
        ClickableBox(
            onClick = { MainRouter.changePage(Navigation.ImageSelectorComponent) },
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
        val currentTextState = remember { mutableStateOf(TextFieldValue(user?.bio ?: "")) }

        val originalTextState = remember { mutableStateOf(TextFieldValue(user?.bio ?:"")) }
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
                value = currentTextState.value,
                onValueChange = { currentTextState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
        if(currentTextState.value.text != originalTextState.value.text){
            IconButton(
                onClick = { user?.let { currentUser ->
                    save(currentUser, originalTextState)
                }},
                modifier = Modifier.align(Alignment.End)



            ) {
                Icon(Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))

            }
        }

    }
}

fun save(userDTO: UserDTO, bio: MutableState<TextFieldValue>){
    val userViewModel = UserViewModel()
    var user = UserDTO(
        id = userDTO.id,
        username = userDTO.username,
        email = userDTO.email,
        bio = bio.value.text,
        photoProfile = userDTO.photoProfile,
        provider = userDTO.provider,
        status = userDTO.status,
        addresses = userDTO.addresses,
        paymentMethods = userDTO.paymentMethods,
        role = userDTO.role,
        reviewsTotalSum = userDTO.reviewsTotalSum,
        reviewsNumber = userDTO.reviewsNumber,
        followersNumber = userDTO.followersNumber,
        followingNumber = userDTO.followingNumber


    )
    userViewModel.saveChange(user)
    MainRouter.changePage(Navigation.SettingsPage)

}




