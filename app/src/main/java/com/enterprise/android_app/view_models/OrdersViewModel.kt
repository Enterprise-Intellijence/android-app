package com.enterprise.android_app.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OrderControllerApi
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.OrderDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrdersViewModel: ViewModel() {
    private val orderControllerApi: OrderControllerApi = OrderControllerApi()
    val orderList = mutableStateListOf<OrderDTO>()
    var currentPage: Int = 0


    fun loadNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val newOrders = withContext(Dispatchers.IO){
                    orderControllerApi.getAllOrdersOfUser(page = currentPage)
                }
                val ordersToAdd = newOrders.content?.toList() ?: emptyList()
                //orderList.addAll(ordersToAdd)
                currentPage++
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }




}