package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.ClickableBox
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import io.swagger.client.models.UserBasicDTO

@Composable
fun SellerRow(user: UserBasicDTO) {

    val rating: Float? = if (user.reviewsNumber != 0) user.reviewsTotalSum?.toFloat()?.div(user.reviewsNumber?.toFloat()!!) else 0f
    ClickableBox(onClick = {
                            CurrentDataUtils.visitedUser = user
                            MainRouter.changePage(Navigation.ProfilePage)
    }, Modifier.height(85.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            LoadImageFromUrl(url = user.photoProfile?.urlPhoto!!, modifier = Modifier
                .padding(16.dp)
                .size(50.dp)
                .clip(CircleShape))

            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = user.username)
                if (rating != null) {
                    RatingBar(
                        modifier = Modifier.padding(0.dp, 5.dp),
                        value = rating,
                        style = RatingBarStyle.Fill(),
                        numOfStars = 5,
                        size = 13.dp,
                        spaceBetween = 3.dp,
                        onRatingChanged = { },
                        onValueChange = { })
                }
            }

            Button(onClick = { /*TODO go to seller chat*/ },
                modifier = Modifier
                    .padding(end = 25.dp, start = 55.dp)
                    .weight(1f)) {
                Text(text = stringResource(id = R.string.ask_seller))
            }
        }
    }


}

