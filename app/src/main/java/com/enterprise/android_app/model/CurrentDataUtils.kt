package com.enterprise.android_app.model

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.enterprise.android_app.model.persistence.AppDatabase
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.AddressDTO
import io.swagger.client.models.PaymentMethodDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ProductDTO
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
    private var _currentAddress: MutableState<AddressDTO?> = mutableStateOf(null)
    private var _Addresses = mutableStateListOf<AddressDTO>()
    private var _PaymentsMethod = mutableStateListOf<PaymentMethodDTO>()
    private var _currentPaymentMethod: MutableState<PaymentMethodDTO?>  = mutableStateOf(null)
    private var _defaultAddress: MutableState<AddressDTO?> = mutableStateOf(null)
    private var _currentAddresses = mutableStateListOf<AddressDTO>()
    private var _defaultPaymentMethod: MutableState<PaymentMethodDTO?> = mutableStateOf(null)


    private var _refreshTokenDB: MutableState<String> = mutableStateOf("")


    private var _showLoadingScreen: MutableState<Boolean> = mutableStateOf(true)

    val chatUserId = mutableStateOf(null as String?)
    val chatProductId = mutableStateOf(null as String?)
    val makeOffer = mutableStateOf(false)


    var _application: Application? = null

    var accessToken: String
        get() = _accessToken.value
        set(newValue) { _accessToken.value = newValue }


    var showLoadingScreen: MutableState<Boolean> = mutableStateOf(false)
        get() = _showLoadingScreen

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

    fun toProductBasicDTO(p: ProductDTO): ProductBasicDTO {

        return ProductBasicDTO(
                id = p.id,
                title = p.title,
                description = p.description,
                uploadDate = p.uploadDate,
                productCost = p.productCost,
                deliveryCost = p.deliveryCost,
                brand = p.brand,
                condition = ProductBasicDTO.Condition.valueOf(p.condition?.name!!),
                likesNumber = p.likesNumber,
                seller = p.seller,
                productImages = p.productImages,
                productCategory = p.productCategory
            )
    }

    var defaultAddress: MutableState<AddressDTO?>
        get() = _defaultAddress
        set(newValue){ _defaultAddress = newValue}

    var defaultPaymentMethod: MutableState<PaymentMethodDTO?>
        get() = _defaultPaymentMethod
        set(newValue){ _defaultPaymentMethod = newValue}
    val addresses: SnapshotStateList<AddressDTO>
        get() = _Addresses

    fun retrieveAddresses(){
        _Addresses.clear()
        _currentUser.value?.addresses?.let { _Addresses.addAll(it.toList()) }
        _currentAddresses.forEach {a ->
            if(a.default == true)
                _defaultAddress.value = a
        }
    }

    var currentPaymentMethodDTO: MutableState<PaymentMethodDTO?>
        get() = _currentPaymentMethod
        set(newValue){ _currentPaymentMethod = newValue}

    val PaymentsMethod: SnapshotStateList<PaymentMethodDTO>
        get() = _PaymentsMethod

    fun retrievePaymentsMethod(){
        _PaymentsMethod.clear()
        _currentUser.value?.paymentMethods?.let { _PaymentsMethod.addAll(it.toList()) }
        _PaymentsMethod.forEach {p ->
            if(p.default == true)
                _defaultPaymentMethod.value = p
        }
    }

    fun setRefresh(refresh_token: String){
        _refreshToken.value = refresh_token
        CoroutineScope(Dispatchers.IO).launch{
            var user = com.enterprise.android_app.model.persistence.User(null, refresh_token)
            var refresh_token2 = AppDatabase.getInstance(_application?.applicationContext!!).userDao().getRefreshToken()
            println(refresh_token2)
            if( refresh_token2 == null){
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().insert(user)
            }
            else{
                AppDatabase.getInstance(_application?.applicationContext!!).userDao().update(refresh_token)
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
        CoroutineScope(Dispatchers.IO).launch {
            _refreshToken.value = AppDatabase.getInstance(_application?.applicationContext!!).userDao().getRefreshToken()

            try {
                var tokenMap: Map<String,String> = userControllerApi.refreshToken()
                if (tokenMap.isNotEmpty()) {
                    _accessToken.value = tokenMap["accessToken"]!!
                    _refreshToken.value = tokenMap["refreshToken"]!!
                    retrieveCurrentUser()
                    UserServices.retriveLikedProducts()
                    _showLoadingScreen.value = false
                    AppRouter.navigateTo(Screen.MainScreen)
                }
            }catch (e: Exception){
                _showLoadingScreen.value = false
            }
        }
    }
}

