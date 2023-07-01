package com.enterprise.android_app.view

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.model.components.ImageCarousell
import com.enterprise.android_app.model.components.SellerRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductPage(){

    var images = listOf(
        R.drawable.kisspng_krypton_flashlight_student_learning_flashlight_png_file_5a7536aa11c1a7_7832310715176311460727,
        R.drawable._0200525_180340,
        R.drawable._0200525_181714,

    )
    val lazyList_state = rememberLazyListState()
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        Scaffold(modifier = Modifier.fillMaxSize() ,

            topBar =  {
                TopAppBar(
                title = { Text("Nome prodotto", modifier = Modifier.padding(horizontal = 16.dp)) },
                navigationIcon = { Icon(Icons.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.padding(16.dp)) },
                actions = {}
                )
            }) {
            contentPadding ->
            LazyColumn( state = lazyList_state , content = { item {
                ImageCarousell(images = images, modifier = Modifier)
            }

                item { 
                    SellerRow(username = "ciccio", rating = 4.5.toFloat(), pic = R.drawable._75px_osculating_circle_svg)
                }
                                  } ,modifier = Modifier.padding(contentPadding))

            }

        }

    }



@Preview
@Composable
fun ProductPagePreview(){
    ProductPage()
}