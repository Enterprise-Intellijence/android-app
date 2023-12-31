package com.enterprise.android_app.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.UserServices
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import io.swagger.client.models.ProductBasicDTO

@Composable
fun ProductCard(navController: NavHostController, product: ProductBasicDTO) {
    var likes by rememberSaveable { mutableStateOf(product.likesNumber) }
    var liked by rememberSaveable { mutableStateOf(UserServices.isProductLiked(product.id))}

    Card(
        modifier = Modifier
            .size(width = 100.dp, height = 250.dp)
            .clickable {
                navController.navigate(Navigation.ProductScreen.route + "?productId=${product.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(painter = /*radius*/rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = product.productImages?.get(0)?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
                        transformations(RoundedCornersTransformation(/*radius*/ 8f))
                    }).build()
            ),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
                    .clip(RectangleShape)
                    .padding(0.dp)
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 2.dp, end = 2.dp)
        ) {

            Text(
                "${product.productCost.price} ${product.productCost.currency}",
                modifier = Modifier
                    .weight(1f)
            )
            Icon(
                imageVector = if (liked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        liked = !liked
                        if (liked) {
                            product.id.let { UserServices.addLikedProduct(it) }
                            likes = likes!! + 1
                        }
                        else {
                            product.id.let { UserServices.removeLikedProduct(it) }
                            likes = likes!! - 1
                        }
                    })

            Text(
                "$likes",
                fontWeight = FontWeight.Light,
                modifier = Modifier
            )
        }
        Text(
            product.title,
            modifier = Modifier.padding(start = 2.dp)
        )

        Text(
            "${product.deliveryCost.price} ${product.deliveryCost.currency} incl.",
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 2.dp, top = 2.dp)
        )
    }
}