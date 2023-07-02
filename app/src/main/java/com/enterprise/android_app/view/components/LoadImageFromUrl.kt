package com.enterprise.android_app.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation

@Composable
fun LoadImageFromUrl(url: String, modifier: Modifier) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            transformations(RoundedCornersTransformation(/*radius*/ 8f))
        }
    )

    Image(
        painter = painter,
        contentDescription = "Image from URL",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}