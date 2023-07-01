package com.enterprise.android_app.model.components

import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SellerRow(username: String, rating: Float, pic: Int) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(pic),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = username)
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

        Button(onClick = { /*TODO go to seller page*/ }) {
            Text(text = stringResource(id = R.string.ask_seller))
        }
    }

}

