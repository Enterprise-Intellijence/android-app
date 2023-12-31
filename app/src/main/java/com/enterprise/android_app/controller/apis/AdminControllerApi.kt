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
import io.swagger.client.models.AdminProductsBody
import io.swagger.client.models.PageUserDTO
import io.swagger.client.models.UserDTO

import io.swagger.client.infrastructure.*

class AdminControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param userId  
     * @return UserDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun banUser(userId: kotlin.String): UserDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/admin/users/ban/{userId}".replace("{" + "userId" + "}", "$userId")
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
     * @param productId  
     * @return void
     */
    fun deleteProduct1(productId: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/admin/products/{productId}".replace("{" + "productId" + "}", "$productId")
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
     * @param productId  
     * @return AdminProductsBody
     */
    @Suppress("UNCHECKED_CAST")
    fun getProduct(productId: kotlin.String): AdminProductsBody {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/admin/products/{productId}".replace("{" + "productId" + "}", "$productId")
        )
        val response = request<AdminProductsBody>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AdminProductsBody
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
     * @param userRole  
     * @param username  (optional)
     * @return PageUserDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getUsers(page: kotlin.Int, size: kotlin.Int, userRole: kotlin.String, username: kotlin.String? = null): PageUserDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
            put("userRole", listOf(userRole.toString()))
            if (username != null) {
                put("username", listOf(username.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/admin/users", query = localVariableQuery
        )
        val response = request<PageUserDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageUserDTO
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
     * @return UserDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun unbanUser(userId: kotlin.String): UserDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/admin/users/unban/{userId}".replace("{" + "userId" + "}", "$userId")
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
     * @param body  
     * @return AdminProductsBody
     */
    @Suppress("UNCHECKED_CAST")
    fun updateProduct(body: AdminProductsBody): AdminProductsBody {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/admin/products/"
        )
        val response = request<AdminProductsBody>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AdminProductsBody
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
