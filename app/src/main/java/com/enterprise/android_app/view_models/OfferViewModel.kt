package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OfferControllerApi
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.OfferCreateDTO
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OfferViewModel : ViewModel() {

    private val TAG = "OfferViewModel"
    private val offerControllerApi: OfferControllerApi = OfferControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val messagePageViewModel = MessagePageViewModel()

    fun cancelOffer(message: MessageDTO) {
        coroutineScope.launch {
            try {
                offerControllerApi.cancelOffer(message.offer?.id!!)
                messagePageViewModel.refreshMessage(message.id!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun acceptOffer(message: MessageDTO) {
        coroutineScope.launch {
            try {
                offerControllerApi.acceptOffer(message.offer?.id!!)
                messagePageViewModel.refreshMessage(message.id!!)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun declineOffer(message: MessageDTO) {
        coroutineScope.launch {
            try {
                offerControllerApi.rejectOffer(message.offer?.id!!)
                messagePageViewModel.refreshMessage(message.id!!)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun makeOffer(product: ProductBasicDTO, amount: CustomMoneyDTO){
        coroutineScope.launch {
            try {
                val offer = OfferCreateDTO(product = product, amount = amount)
                offerControllerApi.createOffer(offer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}