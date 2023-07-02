package com.enterprise.android_app.view.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.ui.theme.AndroidappTheme
import io.swagger.client.models.ProductBasicDTO

@Composable
fun ProductCard(product: ProductBasicDTO){
    Card(modifier = Modifier
        .width(100.dp)
        .height(160.dp)
        .clickable {  },
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        println("img: ${product.productImages?.get(0)?.urlPhoto}");

        Image(painter = rememberImagePainter(
            data = product.productImages?.get(0)?.urlPhoto,
            builder = {
                transformations(RoundedCornersTransformation(/*radius*/ 8f))
            }
        ),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RectangleShape)
                .padding(0.dp)
        )
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)) {
            Text("5.00 $",
                modifier = Modifier
                    .weight(1f))
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = null,
                modifier = Modifier.size(16.dp))
            Text("10",
                fontWeight = FontWeight.Light,
                modifier = Modifier)
        }

        Text("10.00 $ incl.",
            fontSize = 8.sp,
            modifier = Modifier.padding(start = 2.dp)
        )
        Text("category",
            fontSize = 10.sp,
            modifier = Modifier
                .padding(start = 2.dp)
                .alpha(0.5f),
            style = MaterialTheme.typography.displaySmall)
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    AndroidappTheme() {
        //ProductCard()
    }
}