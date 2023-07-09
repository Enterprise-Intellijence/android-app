package com.enterprise.android_app.model

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import io.swagger.client.models.AddressDTO
import com.enterprise.android_app.model.persistence.AppDatabase
import com.enterprise.android_app.model.persistence.User
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.PaymentMethodDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

object CurrentDataUtils {
            private val userControllerApi = UserControllerApi()

    private var _accessToken: MutableState<String> = mutableStateOf("")
    private var _refreshToken: MutableState<String> = mutableStateOf("")
    private var _currentUser: MutableState<UserDTO?> = mutableStateOf(null)
    private var _currentProductId: MutableState<String> = mutableStateOf("")
    private var _visitedUser: MutableState<UserBasicDTO?> = mutableStateOf(null)
    private var _currentAddress: MutableState<AddressDTO?>  = mutableStateOf(null)
    private var _Addresses = mutableStateListOf<AddressDTO>()
    private var _PaymentsMethod = mutableStateListOf<PaymentMethodDTO>()
    private var _currentPaymentMethod: MutableState<PaymentMethodDTO?>  = mutableStateOf(null)

    private var _refreshTokenDB: MutableState<String> = mutableStateOf("")


    private var _hasToCheck: MutableState<Boolean> = mutableStateOf(false)

    val chatUserId = mutableStateOf(null as String?)
    val chatProductId = mutableStateOf(null as String?)
    val makeOffer = mutableStateOf(false)


    var _application: Application? = null

    var accessToken: String
        get() = _accessToken.value
        set(newValue) { _accessToken.value = newValue }


    var hasToCheck: Boolean
        get() = _hasToCheck.value
        set(newValue){ _hasToCheck.value = newValue}

    var refreshToken: String
        get() = _refreshToken.value
        set(newValue) { _refreshToken.value = newValue }

    var refreshTokenDB: String
        get() = _refreshTokenDB.value
        set(newValue) {
            _refreshToken.value = newValue}

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

    var addressDTO: MutableState<AddressDTO?>
        get() = _currentAddress
        set(newValue){ _currentAddress = newValue}

    val addresses: SnapshotStateList<AddressDTO>
        get() = _Addresses

    fun retrieveAddresses(){
        _Addresses.clear()
        _currentUser.value?.addresses?.let { _Addresses.addAll(it.toList()) }
    }

    var currentPaymentMethodDTO: MutableState<PaymentMethodDTO?>
        get() = _currentPaymentMethod
        set(newValue){ _currentPaymentMethod = newValue}

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
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().update(refresh_token)
                println("UPDATE")
            }

        }
    }

        fun refreshToken(){
        CoroutineScope(Dispatchers.IO).launch{
            if( refreshToken != null){
                var tokenMap: Map<String,String> = userControllerApi.refreshToken()
                if (tokenMap.isNotEmpty()) {
                    _accessToken.value = tokenMap["accessToken"]!!
                    _refreshToken.value = tokenMap["refreshToken"]!!
                }
            }
        }
    }


    fun checkRefreshToken(){
        println("QUA CI SONO ARRIVATO")

        CoroutineScope(Dispatchers.IO).launch {
            _refreshToken.value = AppDatabase.getInstance(_application?.applicationContext!!).userDao().getRefreshToken()
            sleep(2000)
            println("REFRESHTOKEN DB --> " + _refreshToken.value)
            try {
                var tokenMap: Map<String,String> = userControllerApi.refreshToken()
                if (tokenMap.isNotEmpty()) {
                    _accessToken.value = tokenMap["accessToken"]!!
                    _refreshToken.value = tokenMap["refreshToken"]!!
                    AppRouter.navigateTo(Screen.MainScreen)
                }
            }catch (e: Exception){
                e.printStackTrace()
                AppRouter.navigateTo(Screen.LoginScreen)
            }
        }
    }

}

