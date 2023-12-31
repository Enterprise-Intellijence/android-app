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
import io.swagger.client.models.PagePaymentMethodBasicDTO
import io.swagger.client.models.PaymentMethodCreateDTO
import io.swagger.client.models.PaymentMethodDTO

import io.swagger.client.infrastructure.*

class PaymentMethodControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return PaymentMethodDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun createPaymentMethod(body: PaymentMethodCreateDTO): PaymentMethodDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/payment-methods"
        )
        val response = request<PaymentMethodDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PaymentMethodDTO
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
    fun deletePaymentMethod(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/payment-methods/{id}".replace("{" + "id" + "}", "$id")
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
     * @param page  
     * @param size  
     * @return PagePaymentMethodBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getMyPaymentMethods(page: kotlin.Int, size: kotlin.Int): PagePaymentMethodBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/payment-methods", query = localVariableQuery
        )
        val response = request<PagePaymentMethodBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PagePaymentMethodBasicDTO
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
     * @return PaymentMethodDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getPaymentMethod(id: kotlin.String): PaymentMethodDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/payment-methods/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<PaymentMethodDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PaymentMethodDTO
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
     * @return PaymentMethodDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun updatePaymentMethod(body: PaymentMethodDTO, id: kotlin.String): PaymentMethodDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/payment-methods/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<PaymentMethodDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PaymentMethodDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
