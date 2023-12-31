package com.enterprise.android_app.view.settings.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.ui.theme.Secondary
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.Github
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Twitter

@Composable
fun AboutPage(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row() {
            Text(
                text = stringResource(R.string.about_description),
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(10.dp),
            )
        }

        Row(
            modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Secondary)
                    .padding(10.dp)
                    .size(30.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Brands.Github,
                    contentDescription = stringResource(R.string.github),
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Secondary)
                    .padding(10.dp)
                    .size(30.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Brands.Instagram,
                    contentDescription = stringResource(R.string.instagram),
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Secondary)
                    .padding(10.dp)
                    .size(30.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Brands.Twitter,
                    contentDescription = stringResource(R.string.twitter),
                    tint = Color.White
                )
            }
        }
    }
}