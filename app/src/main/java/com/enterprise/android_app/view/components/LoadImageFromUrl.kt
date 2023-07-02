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
fun LoadImageFromUrl(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            transformations(RoundedCornersTransformation(/*radius*/ 8f))
        }
    )

    Image(
        painter = painter,
        contentDescription = "Image from URL",
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Crop
    )
}