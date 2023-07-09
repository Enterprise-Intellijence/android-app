package com.enterprise.android_app.view.screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.view.ProductPage
import com.enterprise.android_app.view.components.ImageCarousell
import com.enterprise.android_app.view.components.ProductHeader
import com.enterprise.android_app.view.components.SellerRow
import com.enterprise.android_app.view.components.TabProductComponent
import com.enterprise.android_app.view.components.VerticalDivider
import com.enterprise.android_app.view_models.ProductPageViewModel
import io.swagger.client.models.ProductDTO

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavHostController, productId: String) {


    val productPageViewModel: ProductPageViewModel = viewModel()
    println(CurrentDataUtils.currentUser)
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        if (productPageViewModel.product == null || productPageViewModel.product?.id != productId) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
            productPageViewModel.getProductById(productId)
        }
        else {

            val lazyList_state = rememberLazyListState()
            Scaffold(modifier = Modifier.fillMaxSize(),

                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                productPageViewModel.product?.title!!,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        },
                        navigationIcon = {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.padding(16.dp)
                            )
                        },
                        actions = {}
                    )
                }) { contentPadding ->

                ProductPage(productPageViewModel = productPageViewModel,
                    product = productPageViewModel.product!!,
                    lazyList_state = lazyList_state, padding = contentPadding)
            }
        }

    }
}
