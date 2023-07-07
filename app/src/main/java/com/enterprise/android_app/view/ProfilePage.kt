package com.enterprise.android_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.view.components.Closet
import com.enterprise.android_app.view.components.Reviews
import com.enterprise.android_app.view.settings.updateUser
import com.enterprise.android_app.view_models.ProfileViewModel
import com.enterprise.android_app.view_models.UserViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cubes
import compose.icons.fontawesomeicons.solid.ExclamationCircle
import compose.icons.fontawesomeicons.solid.PencilAlt
import io.swagger.client.models.UserBasicDTO

@Composable
fun ProfilePage(visitedUser: UserBasicDTO?){
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Closet", "Reviews")
    val profileViewModel = ProfileViewModel()
    profileViewModel.visitedUser.value = visitedUser
    profileViewModel.setUserId(visitedUser?.id!!)

    Column(modifier = Modifier.fillMaxWidth()) {

        ProfileInfo(profileViewModel)

        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title)},
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = FontAwesomeIcons.Solid.Cubes,
                                        contentDescription = "Closet",
                                        modifier = Modifier.height(20.dp))
                            1 -> Icon(imageVector = FontAwesomeIcons.Solid.PencilAlt,
                                        contentDescription = "Reviews",
                                        modifier = Modifier.height(20.dp))
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> Closet(profileViewModel)
            1 -> Reviews(profileViewModel)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileInfo(profileViewModel: ProfileViewModel) {
    val visitedUser = profileViewModel.visitedUser
    var isFollowing = profileViewModel.isFollowing

    LaunchedEffect(key1 = profileViewModel.visitedUser.value?.id) {
        profileViewModel.updateUser()
        profileViewModel.checkFollowing()
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(start = 10.dp, top = 2.dp)
        ) {
            Image(painter = rememberImagePainter(
                data = visitedUser.value?.photoProfile?.urlPhoto,
                builder = {
                    transformations(RoundedCornersTransformation(4f))
                }
            ),
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape))
        }

        Column(
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                .fillMaxWidth(0.5f)
        ) {
            Row() {
                Text(visitedUser.value?.username.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontWeight = FontWeight.Bold)

            }

            Row() {
                Text("Followers: ", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,)
                Text(visitedUser.value?.followersNumber.toString())
            }

            Row() {
                Text("Following: ", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,)
                Text(visitedUser.value?.followingNumber.toString())
            }

            Row() {
                Text("Reviews: ", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall)
                Text(visitedUser.value?.reviewsNumber.toString())
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {

            if (visitedUser.value?.id != CurrentDataUtils.currentUser?.id) {
                if (profileViewModel.isFollowing.value == null) {
                    profileViewModel.checkFollowing()
                }
                if (isFollowing.value == true) {
                    Button(onClick = {
                        profileViewModel.unfollow()
                        isFollowing.value = false
                    }) {
                        Text("Unfollow")
                    }
                } else {
                    Button(onClick = {
                        profileViewModel.follow()
                        isFollowing.value = true
                    }) {
                        Text("Follow")
                    }
                }
            } else {
                Button(onClick = { MainRouter.changePage(Navigation.ProfileDetailsScreen)}) {
                    Text("Edit profile")
                }
            }
        }
    }

    Row() {
        Text("Bio",
            Modifier.padding(top = 10.dp, start = 10.dp),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight . Bold,
        )
    }

    Row(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(visitedUser.value?.bio ?: "Wow, such empty and boring bio, much sad, very bad, wow, incredible, amazing, fuck you",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 5.dp, start = 10.dp, end= 10.dp))
        }
    }
}
