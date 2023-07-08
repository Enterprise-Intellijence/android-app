package com.enterprise.android_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.components.Closet
import com.enterprise.android_app.view.components.Reviews
import com.enterprise.android_app.view_models.ProfileViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cubes
import compose.icons.fontawesomeicons.solid.PencilAlt
import io.swagger.client.models.UserBasicDTO

@Composable
fun ProfilePage(visitedUser: UserBasicDTO?){
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(stringResource(id = R.string.Closet), stringResource(id = R.string.Reviews))
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
                                        contentDescription = stringResource(id = R.string.Closet),
                                        modifier = Modifier.height(20.dp))
                            1 -> Icon(imageVector = FontAwesomeIcons.Solid.PencilAlt,
                                        contentDescription = stringResource(id = R.string.Reviews),
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
    val isFollowing = remember { profileViewModel.isFollowing }

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
                contentDescription = stringResource(id = R.string.profile_picture),
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
                Text(stringResource(id = R.string.followers_label), fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,)
                Text(visitedUser.value?.followersNumber.toString())
            }

            Row() {
                Text(
                    stringResource(id = R.string.following_label), fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(visitedUser.value?.followingNumber.toString())
            }

            Row() {
                Text(stringResource(id = R.string.reviews_label), fontWeight = FontWeight.Bold,
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
                        Text(stringResource(id = R.string.unfollow))
                    }
                } else {
                    Button(onClick = {
                        profileViewModel.follow()
                        isFollowing.value = true
                    }) {
                        Text(stringResource(id = R.string.follow))
                    }
                }
            } else {
                Button(onClick = { MainRouter.changePage(Navigation.ProfileDetailsPage)}) {
                    Text(stringResource(id = R.string.edit_profile))
                }
            }
        }
    }

    Row() {
        Text(
            stringResource(id = R.string.bio),
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
            Text(visitedUser.value?.bio ?: stringResource(id = R.string.empty_bio),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 5.dp, start = 10.dp, end= 10.dp))
        }
    }
}
