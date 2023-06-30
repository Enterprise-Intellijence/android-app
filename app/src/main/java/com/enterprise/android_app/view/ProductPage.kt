package com.enterprise.android_app.view

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductPage(){

    var images = listOf(
        R.drawable.kisspng_krypton_flashlight_student_learning_flashlight_png_file_5a7536aa11c1a7_7832310715176311460727,
        R.drawable._0200525_180340,
        R.drawable._0200525_180340,

    )

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
            Box(modifier = Modifier.padding(contentPadding)) {
                val pagerState = rememberPagerState()
                val scope = rememberCoroutineScope()

                HorizontalPager(
                    modifier =Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    pageCount = images.size,
                    state = pagerState,
                    key = { images[it] },
                    pageSize = PageSize.Fill) {
                    index ->
                    Image(
                        painter = painterResource(id = images[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth() )

                }


                Box(
                    modifier = Modifier
                        .offset(y = -(16).dp)
                        .fillMaxWidth(0.5f)
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(8.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage - 1
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Go back"
                        )
                    }
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Go forward"
                        )
                    }
                }
            }
            }

        }

    }



@Preview
@Composable
fun ProductPagePreview(){
    ProductPage()
}