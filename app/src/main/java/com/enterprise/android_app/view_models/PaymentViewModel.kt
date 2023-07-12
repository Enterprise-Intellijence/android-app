package com.enterprise.android_app.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    public val updated : MutableState<Boolean> = mutableStateOf(false)
    val localUpdated: MutableState<Boolean> = mutableStateOf(false)

    fun updatePayment(payment: PaymentMethodDTO){
        coroutineScope.launch {
            try{

                withContext(Dispatchers.IO) {
                    if(payment.id != null)
                        paymentControllerApi.updatePaymentMethod(payment,payment.id)
                }

                updated.value = true

                //MainRouter.changePage(Navigation.PaymentsPage)

            } catch (e: Exception) {
                updated.value = false
                e.printStackTrace()
            }
            CurrentDataUtils.retrieveCurrentUser()
            CurrentDataUtils.retrievePaymentsMethod()
            localUpdated.value = true


        }


    }



    fun createPayment(payment: PaymentMethodCreateDTO){
        coroutineScope.launch {
            try{
                withContext(Dispatchers.IO) {
                    paymentControllerApi.createPaymentMethod(payment)
                }
                updated.value = true


            } catch (e: Exception) {
                updated.value = false
                e.printStackTrace()
            }

            CurrentDataUtils.retrieveCurrentUser()
            CurrentDataUtils.retrievePaymentsMethod()
            //MainRouter.changePage(Navigation.PaymentsPage)
            localUpdated.value = true

        }

    }

    fun deletePayment(id: String){
        coroutineScope.launch {
            try{

                val response = withContext(Dispatchers.IO) {
                    paymentControllerApi.deletePaymentMethod(id)
                }
                updated.value = true

                CurrentDataUtils.retrieveCurrentUser()
                CurrentDataUtils.retrievePaymentsMethod()
                //MainRouter.changePage(Navigation.PaymentsPage)
            } catch (e: Exception) {
                updated.value = false

                e.printStackTrace()
            }
            localUpdated.value = true


        }

    }



}