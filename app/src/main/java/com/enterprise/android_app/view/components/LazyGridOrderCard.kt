package com.enterprise.android_app.view.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.OrderDTO


@Composable
fun LazyGridOrderCard(
    orders: SnapshotStateList<OrderBasicDTO>,
    lazyGridState: LazyGridState,
    onLoadMore: () -> Unit

){
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        state = lazyGridState,
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(orders){ index, order ->
            OrderCard(order = order)
            val contentSize = orders.size
            if (index == contentSize - 1 && lazyGridState.firstVisibleItemIndex + lazyGridState.layoutInfo.visibleItemsInfo.size >= contentSize){
                onLoadMore()
            }
        }
    }
}