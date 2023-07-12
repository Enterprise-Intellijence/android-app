package com.enterprise.android_app.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.model.CurrentDataUtils.visitedUser
import com.enterprise.android_app.navigation.Navigation
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import io.swagger.client.models.ReviewDTO

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ReviewCard(review: ReviewDTO, navController: NavController) {

    Card(
        modifier = Modifier.padding(8.dp)
            .clickable {
                navController.navigate(Navigation.ProfilePage.route + "?visitedUserId=${review.reviewer.id}")
            }){
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Row() {
                        Image(painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = review.reviewer.photoProfile?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
                                    transformations(RoundedCornersTransformation(4f))
                                }).build()
                        ),
                            contentDescription = "profile picture",
                            modifier = Modifier
                                .size(30.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape))

                        Text(text = review.reviewer.username,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(start = 10.dp))
                    }
                }

                Column() {
                    RatingBar(
                        modifier = Modifier.padding(bottom = 8.dp),
                        value = review.vote.toFloat(),
                        style = RatingBarStyle.Fill(),
                        numOfStars = 5,
                        size = 13.dp,
                        spaceBetween = 3.dp,
                        onRatingChanged = { },
                        onValueChange = { })
                }
            }



            Row() {
                Text(text = review.description.toString())
            }
        }
    }
}