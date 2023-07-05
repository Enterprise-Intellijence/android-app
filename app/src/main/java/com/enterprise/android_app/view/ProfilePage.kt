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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.view.components.Closet
import com.enterprise.android_app.view.components.Reviews
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cubes
import compose.icons.fontawesomeicons.solid.PencilAlt
import io.swagger.client.models.UserBasicDTO

@Composable
fun ProfilePage(visitedUser: UserBasicDTO?){
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Closet", "Reviews")

    Column(modifier = Modifier.fillMaxWidth()) {

        ProfileInfo(visitedUser)

        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
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
            0 -> Closet(visitedUser?.id!!)
            1 -> Reviews()
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileInfo(visitedUser: UserBasicDTO?) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Image(painter = rememberImagePainter(
                data = visitedUser?.photoProfile?.urlPhoto,
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
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
        ) {
            Row() {
                Text(visitedUser?.username.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontWeight = FontWeight.Bold)

            }

            Row() {
                Text("Followers: ", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,)
                Text(visitedUser?.followersNumber.toString())
            }

            Row() {
                Text("Following: ", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,)
                Text(visitedUser?.followingNumber.toString())
            }

            Row() {
                Text("Reviews: ", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall)
                Text(visitedUser?.reviewsNumber.toString())
            }

            Row() {
                Text("Bio",
                    Modifier.padding(top = 10.dp),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight . Bold,
                )
            }


            Row() {
                Text(visitedUser?.bio ?: "Wow, such empty",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 5.dp))
            }
        }
    }
}
