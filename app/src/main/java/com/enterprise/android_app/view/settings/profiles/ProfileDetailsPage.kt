package com.enterprise.android_app.view.settings.profiles

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.components.ImageSelectorComponent
import com.enterprise.android_app.view.settings.updateUser
import com.enterprise.android_app.view_models.ImageViewModel
import com.enterprise.android_app.view_models.UserViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ProfileDetailsPage(navController: NavHostController) {


    val context = LocalContext.current
    val userViewModel = remember {
        UserViewModel()
    }
    val updated = userViewModel.updated
    var user: MutableState<UserDTO?> = remember {
        mutableStateOf(CurrentDataUtils.currentUser)
    }

    val currentTextState = remember { mutableStateOf(TextFieldValue(user.value?.bio ?: "")) }
    val originalTextState = remember { mutableStateOf(TextFieldValue(user.value?.bio ?:"")) }

    val mContext = LocalContext.current
    val mText = stringResource(id = R.string.bioUpdated)
    val notUpdated = stringResource(id = R.string.bioNotUpdated)

    val localUpdate = userViewModel.localUpdated

    if(localUpdate.value){
        localUpdate.value = false
        if(updated.value){
            mToast(mContext, mText )
            originalTextState.value = currentTextState.value

        }
        else{
            mToast(mContext,notUpdated)
            currentTextState.value = originalTextState.value
        }
    }


    var modifier = Modifier.fillMaxWidth()
    val focusRequester = remember {FocusRequester()}
    val focusManager = LocalFocusManager.current

    val imageViewModel: ImageViewModel = viewModel()

    Column(modifier = modifier ) {

            Row(modifier = Modifier.padding(8.dp)) {
                Image(painter = /*radius*/rememberAsyncImagePainter(
                    ImageRequest.Builder(
                        LocalContext.current
                    ).data(data = user.value?.photoProfile?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
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
                ImageSelectorComponent(onChange = { uri, stream ->
                    if(user.value?.photoProfile != null && user.value?.photoProfile?.urlPhoto!= "") {
                        val updated = imageViewModel.updateUserImage(uri, stream!!)
                        if(updated) {
                            mToast(context, "User image updated")
                            // TODO: prendere la nuova immagine e mostrarla
                        }
                        else mToast(context, "Error on update of user image")
                    }
                    else {
                        val saved = imageViewModel.saveImage(uri, stream!!)
                        if(saved) {
                            mToast(context, "User image saved")
                            // TODO: prendere la nuova immagine e mostrarla
                        }
                        else mToast(context, "Error on save of user image")
                    }
                })
                /*Row() {
                    Column(modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp))
                    {
                        Text(text = stringResource(id = R.string.changePhoto), style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
                    }
                    Icon(Icons.Filled.KeyboardArrowRight,contentDescription = stringResource (R.string.changePhoto))

                }*/
            }


        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
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
                    .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
        }
        if(currentTextState.value.text != originalTextState.value.text){
            IconButton(
                onClick = {
                    user.value?.let { userViewModel.saveChange(userDTO = it.copy(bio = currentTextState.value.text))}
                    focusManager.clearFocus()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))
            }
        }
    }
}


fun mToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

