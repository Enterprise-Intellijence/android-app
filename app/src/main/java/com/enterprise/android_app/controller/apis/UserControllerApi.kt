/**
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.apis

import com.enterprise.android_app.controller.BasePath
import io.swagger.client.models.AddressDTO
import io.swagger.client.models.LoginWithGoogleBody
import io.swagger.client.models.PageOfferBasicDTO
import io.swagger.client.models.PageOrderBasicDTO
import io.swagger.client.models.PageProductBasicDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserDTO

import io.swagger.client.infrastructure.*
import io.swagger.client.models.PageUserBasicDTO

class UserControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param token  
     * @return void
     */
    fun activate(token: kotlin.String): Unit {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("token", listOf(token.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/activate", query = localVariableQuery
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param username  
     * @param password  
     * @return kotlin.collections.Map<kotlin.String, kotlin.String>
     */
    @Suppress("UNCHECKED_CAST")
    fun authenticate(username: kotlin.String, password: kotlin.String): kotlin.collections.Map<kotlin.String, kotlin.String> {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("username", listOf(username.toString()))
            put("password", listOf(password.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/authenticate", query = localVariableQuery
        )
        val response = request<kotlin.collections.Map<kotlin.String, kotlin.String>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.collections.Map<kotlin.String, kotlin.String>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param oldPassword  
     * @param newPassword  
     * @return void
     */
    fun changePassword(oldPassword: kotlin.String, newPassword: kotlin.String): Unit {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("oldPassword", listOf(oldPassword.toString()))
            put("newPassword", listOf(newPassword.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/changePassword", query = localVariableQuery
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun deleteUser(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/users/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param username  
     * @return UserBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun findByUsername(username: kotlin.String): UserBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("username", listOf(username.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/find-by-username", query = localVariableQuery
        )
        val response = request<UserBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param userId  
     * @return AddressDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getDefaultAddress(userId: kotlin.String): AddressDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("userId", listOf(userId.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/default-address", query = localVariableQuery
        )
        val response = request<AddressDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AddressDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param page  
     * @param size  
     * @return PageProductBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getLikedProducts(page: kotlin.Int, size: kotlin.Int): PageProductBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/liked/", query = localVariableQuery
        )
        val response = request<PageProductBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageProductBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param page  
     * @param size  
     * @return PageOfferBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getMyOffers(page: kotlin.Int, size: kotlin.Int): PageOfferBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/me/offers", query = localVariableQuery
        )
        val response = request<PageOfferBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageOfferBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param page  
     * @param size  
     * @return PageOrderBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getMyOrders(page: kotlin.Int, size: kotlin.Int): PageOrderBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/me/orders", query = localVariableQuery
        )
        val response = request<PageOrderBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageOrderBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param idTokenString  
     * @return kotlin.collections.Map<kotlin.String, kotlin.String>
     */
    @Suppress("UNCHECKED_CAST")
    fun googleAuth(idTokenString: kotlin.String): kotlin.collections.Map<kotlin.String, kotlin.String> {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("idTokenString", listOf(idTokenString.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/google-auth", query = localVariableQuery
        )
        val response = request<kotlin.collections.Map<kotlin.String, kotlin.String>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.collections.Map<kotlin.String, kotlin.String>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun like(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/like/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @return kotlin.collections.Map<kotlin.String, kotlin.String>
     */
    @Suppress("UNCHECKED_CAST")
    fun loginWithGoogle(body: LoginWithGoogleBody): kotlin.collections.Map<kotlin.String, kotlin.String> {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("body", listOf(body.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/login-with-google", query = localVariableQuery
        )
        val response = request<kotlin.collections.Map<kotlin.String, kotlin.String>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.collections.Map<kotlin.String, kotlin.String>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return void
     */
    fun logout(): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
            "/api/v1/users/logout"
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return UserDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun me(): UserDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/me"
        )
        val response = request<UserDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return kotlin.collections.Map<kotlin.String, kotlin.String>
     */
    @Suppress("UNCHECKED_CAST")
    fun refreshToken(): kotlin.collections.Map<kotlin.String, kotlin.String> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/refreshToken"
        )
        val response = request<kotlin.collections.Map<kotlin.String, kotlin.String>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.collections.Map<kotlin.String, kotlin.String>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param username  
     * @param email  
     * @param password  
     * @return void
     */
    fun register(username: kotlin.String, email: kotlin.String, password: kotlin.String): Unit {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("username", listOf(username.toString()))
            put("email", listOf(email.toString()))
            put("password", listOf(password.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/register", query = localVariableQuery
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param email  
     * @return void
     */
    fun resetPassword(email: kotlin.String): Unit {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("email", listOf(email.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/resetPassword", query = localVariableQuery
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param token  
     * @return void
     */
    fun resetPasswordToken(token: kotlin.String): Unit {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("token", listOf(token.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/getNewPassword", query = localVariableQuery
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param username
     * @param page
     * @param size
     * @return PageUserBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun searchByUsername(username: kotlin.String, page: kotlin.Int, size: kotlin.Int): PageUserBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("username", listOf(username.toString()))
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/api/v1/users/search-by-username", query = localVariableQuery
        )
        val response = request<PageUserBasicDTO>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageUserBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun unlike(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/users/unlike/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param id  
     * @return UserDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun updateUser(body: UserDTO, id: kotlin.String): UserDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PATCH,
                "/api/v1/users/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<UserDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return UserBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun userById(id: kotlin.String): UserBasicDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/users/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<UserBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
