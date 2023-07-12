package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OrderControllerApi
import io.swagger.client.models.Order
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.OrderDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrdersViewModel: ViewModel() {
    private val orderControllerApi: OrderControllerApi = OrderControllerApi()
    val orderList = mutableStateListOf<OrderBasicDTO>()
    var currentPage: Int = 0
    var orderDTO: MutableState<OrderDTO?> = mutableStateOf(null)


    fun loadNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val newOrders = withContext(Dispatchers.IO){
                    orderControllerApi.getAllOrdersOfUser(page = currentPage)
                }
                val ordersToAdd = newOrders.content?.toList() ?: emptyList()
                orderList.addAll(ordersToAdd)
                currentPage++
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    fun loadSingleOrder(orderId: String){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val order = orderControllerApi.getOrder(orderId)
                orderDTO.value = order
                println(orderDTO.value!!.id)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}