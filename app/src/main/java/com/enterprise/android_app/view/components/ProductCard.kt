package com.enterprise.android_app.view.components

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.swagger.client.models.ProductBasicDTO
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun ProductCard(product: ProductBasicDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.title ?: "No title",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()

            )
            LoadImageFromUrl(url = product.productImages?.get(0)?.urlPhoto ?: "")

        }



    }
    Log.d("ProductImageURL", product.productImages?.get(0)?.urlPhoto ?: "")

}
