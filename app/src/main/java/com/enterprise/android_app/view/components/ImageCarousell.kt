package com.enterprise.android_app.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.enterprise.android_app.view.screen.ImageFullScreen
import io.swagger.client.models.ProductImageDTO

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousell(images: List<ProductImageDTO>, modifier: Modifier) {
    Box(modifier = modifier) {
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        val selectedImageUrl = remember { mutableStateOf("") }

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            pageCount = images.size,
            state = pagerState,
            key = { images[it].id!! },
            pageSize = PageSize.Fill) {
                index ->
                    LoadImageFromUrl(
                        url = images[index].urlPhoto!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedImageUrl.value = images[index].urlPhoto!! }
                    )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in images.indices) {
                PointIndicator(selected = i == pagerState.currentPage)
            }
        }

        if (selectedImageUrl.value.isNotEmpty()) {
            Dialog(onDismissRequest = {
                    selectedImageUrl.value = ""
                },
                content = {

                        ImageFullScreen(
                            imageUrl = selectedImageUrl.value,
                            onClose = {
                                selectedImageUrl.value = ""
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                }
            )
        }
    }
}

@Composable
fun PointIndicator(selected: Boolean) {
    val color = if (selected) Color.White else Color.Gray
    val size = if (selected) 10.dp else 8.dp

    Spacer(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
            .padding(4.dp)
    )
    
    Spacer(modifier = Modifier.width(4.dp))
}
