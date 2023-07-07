package com.enterprise.android_app.view_models

import android.util.Log
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import io.swagger.client.apis.DeliveryControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.infrastructure.ResponseType
import io.swagger.client.models.Address
import io.swagger.client.models.AddressCreateDTO
import io.swagger.client.models.AddressDTO
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DeliveryViewModel {
    private var deliveryControllerApi: DeliveryControllerApi = DeliveryControllerApi()


    private val coroutineScope = CoroutineScope(Dispatchers.IO)



    fun updateAddress(addressDTO: AddressDTO){
        coroutineScope.launch {
            try {
                var address : AddressDTO
                val addresses = CurrentDataUtils.currentUser?.addresses

                withContext(Dispatchers.IO) {
                    deliveryControllerApi.updateAddress(addressDTO,addressDTO.id)
                    /*addresses?.forEachIndexed { index, oldAddress ->
                        if (oldAddress.id == address.id) {
                            addresses[index] = address
                        }
                    }*/
                    CurrentDataUtils.retrieveCurrentUser()
                    CurrentDataUtils.retrieveAddresses()


                    //return@withContext address.id!=""

                }

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
        MainRouter.changePage(Navigation.ShippingPage)


    }

    fun createAddress(createAddressDTO: AddressCreateDTO){
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    deliveryControllerApi.createAddress(createAddressDTO)

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            CurrentDataUtils.retrieveCurrentUser()
            CurrentDataUtils.retrieveAddresses()

            MainRouter.changePage(Navigation.ShippingPage)

        }


    }

    fun deleteAddress(id:String) {


        coroutineScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    deliveryControllerApi.deleteAddress(id)

                }
                Log.d("RESPONSE SSSSS",response.toString())


                /*if (response) {
                    CurrentDataUtils.retrieveCurrentUser()
                    CurrentDataUtils.retrieveAddresses()
                    MainRouter.changePage(Navigation.ShippingPage)
                }*/
            } catch (e: Exception){
                e.printStackTrace()
            }
            CurrentDataUtils.retrieveCurrentUser()
            CurrentDataUtils.retrieveAddresses()

            MainRouter.changePage(Navigation.ShippingPage)
        }



    }



}