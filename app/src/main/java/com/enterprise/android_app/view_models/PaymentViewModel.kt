package com.enterprise.android_app.view_models

import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import io.swagger.client.apis.PaymentMethodControllerApi
import io.swagger.client.models.AddressDTO
import io.swagger.client.models.PaymentMethodCreateDTO
import io.swagger.client.models.PaymentMethodDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaymentViewModel {
    private var paymentControllerApi: PaymentMethodControllerApi = PaymentMethodControllerApi()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun updatePayment(payment: PaymentMethodDTO){
        coroutineScope.launch {
            try{

                withContext(Dispatchers.IO) {
                    if(payment.id != null)
                        paymentControllerApi.updatePaymentMethod(payment,payment.id)



                }
                CurrentDataUtils.retrieveCurrentUser()
                CurrentDataUtils.retrievePaymentsMethod()
                MainRouter.changePage(Navigation.PaymentsPage)

            } catch (e: Exception) {

                e.printStackTrace()
            }


        }


    }



    fun createPayment(payment: PaymentMethodCreateDTO){
        coroutineScope.launch {
            try{
                withContext(Dispatchers.IO) {
                    paymentControllerApi.createPaymentMethod(payment)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            CurrentDataUtils.retrieveCurrentUser()
            CurrentDataUtils.retrievePaymentsMethod()

            MainRouter.changePage(Navigation.PaymentsPage)

        }

    }

    fun deletePayment(id: String){
        coroutineScope.launch {
            try{

                val response = withContext(Dispatchers.IO) {
                    paymentControllerApi.deletePaymentMethod(id)
                }

                CurrentDataUtils.retrieveCurrentUser()
                CurrentDataUtils.retrievePaymentsMethod()
                MainRouter.changePage(Navigation.PaymentsPage)
            } catch (e: Exception) {

                e.printStackTrace()
            }


        }

    }



}