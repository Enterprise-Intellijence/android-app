package com.enterprise.android_app.model

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import io.swagger.client.models.AddressDTO
import com.enterprise.android_app.model.persistence.AppDatabase
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.PaymentMethodDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CurrentDataUtils {
    private val userControllerApi = UserControllerApi()

    private var _accessToken: MutableState<String> = mutableStateOf("")
    private var _refreshToken: MutableState<String> = mutableStateOf("")
    private var _currentUser: MutableState<UserDTO?> = mutableStateOf(null)
    private var _currentProductId: MutableState<String> = mutableStateOf("")
    private var _visitedUser: MutableState<UserBasicDTO?> = mutableStateOf(null)
    private var _currentAddress: AddressDTO? = null
    private var _currentAddresses = mutableStateListOf<AddressDTO>()
    private var _PaymentsMethod = mutableStateListOf<PaymentMethodDTO>()
    private var _currentPaymentMethod: PaymentMethodDTO? = null

    var _application: Application? = null

    var accessToken: String
        get() = _accessToken.value
        set(newValue) { _accessToken.value = newValue }

    var refreshToken: String
        get() = _refreshToken.value
        set(newValue) { _refreshToken.value = newValue }

    var currentUser: UserDTO? = null
        get() = _currentUser.value

    fun retrieveCurrentUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _currentUser.value = userControllerApi.me()
                retrieveAddresses()
                retrievePaymentsMethod()

            } catch (e: Exception) {
                println(e)
            }
        }
    }

    var currentProductId: String
        get() = _currentProductId.value
        set(newValue) { _currentProductId.value = newValue }

    var visitedUser: UserBasicDTO
        get() = _visitedUser.value!!
        set(newValue) { _visitedUser.value = newValue }

    fun toUserBasicDTO(u: UserDTO): UserBasicDTO {
        return UserBasicDTO(
            id = u.id,
            username = u.username,
            bio = u.bio,
            photoProfile = u.photoProfile,
            reviewsTotalSum = u.reviewsTotalSum,
            reviewsNumber = u.reviewsNumber,
            followersNumber = u.followersNumber,
            followingNumber = u.followingNumber
        )
    }

    var addressDTO: AddressDTO?
        get() = _currentAddress
        set(newValue){ _currentAddress = newValue}

    var currentPaymentMethodDTO: PaymentMethodDTO?
        get() = _currentPaymentMethod
        set(newValue){ _currentPaymentMethod = newValue}

    val addresses: SnapshotStateList<AddressDTO>
        get() = _currentAddresses


    fun retrieveAddresses(){
        _currentAddresses.clear()
        _currentUser.value?.addresses?.let { _currentAddresses.addAll(it.toList()) }
    }

    val PaymentsMethod: SnapshotStateList<PaymentMethodDTO>
        get() = _PaymentsMethod

    fun retrievePaymentsMethod(){
        _PaymentsMethod.clear()
        _currentUser.value?.paymentMethods?.let { _PaymentsMethod.addAll(it.toList()) }
    }
    fun setRefresh(refresh_token: String){
        _refreshToken.value = refresh_token
        CoroutineScope(Dispatchers.IO).launch{
            var user = com.enterprise.android_app.model.persistence.User(null, refresh_token)
            var refresh_token2 = AppDatabase.getInstance(_application?.applicationContext!!).userDao().getRefreshToken()
            println(refresh_token2)
            if( refresh_token2 == null){
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().insert(user)
                println("INSERT")
            }
            else{
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().update(user)
                println("UPDATE")
            }

        }
    }

    fun refreshToken(){
        CoroutineScope(Dispatchers.IO).launch{
            //TODO get refreshtoken from db
            if( refreshToken != null){
                var tokenMap: Map<String,String> = userControllerApi.refreshToken()
                if (tokenMap.isNotEmpty()) {
                    _accessToken.value = tokenMap["accessToken"]!!
                    _refreshToken.value = tokenMap["refreshToken"]!!
                }
                //TODO persist new refreshtoken
            }
        }
    }

}

