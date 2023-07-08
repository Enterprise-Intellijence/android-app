package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.SizeDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SizeViewModel : ViewModel() {

    private val productControllerApi: ProductControllerApi = ProductControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var _sizesList: MutableStateFlow<List<SizeDTO>> = MutableStateFlow(emptyList())
    private var _sizesByCat: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())


    val sizes: MutableStateFlow<List<SizeDTO>>
        get() = _sizesList

    val sizesByCat: MutableStateFlow<List<String>>
        get() = _sizesByCat

    fun getSizes() {
            coroutineScope.launch {
                try {
                    val allSizes = withContext(Dispatchers.IO) {
                        productControllerApi.getSizesList()
                    }
                    _sizesList.emit(allSizes as List<SizeDTO>)

                    println("sizesss: " + _sizesList.value)

                } catch (e: Exception) {
                    println("error")
                    e.printStackTrace()
                }
            }
    }

    fun getSizesByCategory(category: String) {
        coroutineScope.launch {
            try {
                val sizes = withContext(Dispatchers.IO) {
                    productControllerApi.getSizesListByCategory(category)
                }
                _sizesByCat.emit(sizes as List<String>)

                println("sizesss by cattt: " + _sizesByCat.value[0])

            } catch (e: Exception) {
                println("error")
                e.printStackTrace()
            }
        }
    }
}