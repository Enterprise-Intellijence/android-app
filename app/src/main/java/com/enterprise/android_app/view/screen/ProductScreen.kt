package com.enterprise.android_app.view.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.UserServices
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view.components.ImageCarousell
import com.enterprise.android_app.view.components.ProductHeader
import com.enterprise.android_app.view.components.SellerRow
import com.enterprise.android_app.view.components.TabProductComponent
import com.enterprise.android_app.view.components.VerticalDivider
import com.enterprise.android_app.view_models.ProductPageViewModel
import io.swagger.client.models.ProductDTO

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductScreen(navController: NavHostController, productId: String) {


    val productPageViewModel: ProductPageViewModel = viewModel()
    val lazyListState = rememberLazyListState()
    val product = productPageViewModel.product
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        if (productPageViewModel.product == null || productPageViewModel.product?.id != productId) {
            CircularProgressIndicator(modifier = Modifier.size(40.dp))
            productPageViewModel.getProductById(productId)
        } else {
            LazyColumn(state = lazyListState, content = {
                item {
                    ImageCarousell(images = product?.productImages!!.toList(), modifier = Modifier)
                }
                item {
                    Divider(Modifier.fillMaxWidth(), color = Color.Gray)
                }
                item {
                    SellerRow(product?.seller!!, onClick = {
                        navController.navigate(Navigation.ProfilePage.route + "?visitedUserId=${product.seller.id}")
                    }) {
                        CurrentDataUtils.chatUserId.value = product.seller.id
                        CurrentDataUtils.chatProductId.value = product.id
                        navController.navigate(Navigation.MessagesPage.route)
                    }
                }
                item {
                    Divider(Modifier.fillMaxWidth(), color = Color.Gray)
                }
                item {
                    ProductHeader(
                        name = product?.title!!,
                        condition = product.condition.toString(),
                        price = product.productCost.price!!
                    )
                }
                if (product?.seller?.id!! == CurrentDataUtils.currentUser?.id) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate(Navigation.EditProductPage.route + "?productId=${product.id}")
                                },
                                colors = ButtonDefaults.outlinedButtonColors(),
                                modifier = Modifier
                                    .height(45.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp)
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(7.dp)
                                    ),
                                shape = RoundedCornerShape(7.dp)
                            ) {
                                Text(text = stringResource(R.string.edit))
                            }
                        }
                    }
                } else {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate(Navigation.PurchasePage.route)
                                },
                                modifier = Modifier
                                    .height(45.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp),
                                shape = RoundedCornerShape(7.dp)
                            ) {
                                Text(text = stringResource(R.string.buy_now))
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {
                                    CurrentDataUtils.chatUserId.value = product.seller.id
                                    CurrentDataUtils.chatProductId.value = product.id
                                    CurrentDataUtils.makeOffer.value = true
                                    navController.navigate(Navigation.MessagesPage.route)
                                },
                                colors = ButtonDefaults.outlinedButtonColors(),
                                modifier = Modifier
                                    .height(45.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp)
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(7.dp)
                                    ),
                                shape = RoundedCornerShape(7.dp)
                            ) {
                                Text(text = stringResource(R.string.make_an_offer))
                            }
                        }
                    }
                }

                item {
                    Divider(Modifier.fillMaxWidth(), color = Color.Gray)
                }
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(
                            onClick = {
                                if (UserServices.isProductLiked(product.id!!)) {
                                    UserServices.removeLikedProduct(product.id!!)
                                } else {
                                    UserServices.addLikedProduct(product.id!!)
                                }
                            },
                            colors = ButtonDefaults.outlinedButtonColors(),
                            shape = RectangleShape,
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 54.dp)
                                .border(
                                    0.dp,
                                    Color.Transparent
                                )
                        ) {
                            Icon(
                                if (UserServices.isProductLiked(product.id!!)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Favourite",
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            Text(text = stringResource(id = R.string.favourite))
                        }
                        VerticalDivider(color = Color.Gray)
                        Button(
                            onClick = {
                                val url = productPageViewModel.getProductShareLink(product)
                                val text = "Hey, check out this product on Svinted: ${product.title} for ${product.productCost.price} ${product.productCost.currency}\n\n$url"



                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, text)
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)

                                ContextCompat.startActivity(context, shareIntent, null)

                            },
                            colors = ButtonDefaults.outlinedButtonColors(),
                            shape = RectangleShape,
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 54.dp)
                                .border(
                                    0.dp,
                                    Color.Transparent
                                )
                        ) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share",
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            Text(text = stringResource(R.string.share))
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = stringResource(R.string.item_description),
                                modifier = Modifier.padding(16.dp)
                            )
                            Column(modifier = Modifier.height(IntrinsicSize.Min)) {
                                Text(
                                    text = product.description!!,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                    }
                }
                item {
                    TabProductComponent(productPageViewModel = productPageViewModel, navController = navController)
                }
            })

            }
        }

}
