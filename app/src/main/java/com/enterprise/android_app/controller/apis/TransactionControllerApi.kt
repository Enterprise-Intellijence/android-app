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

import io.swagger.client.models.TransactionCreateDTO
import io.swagger.client.models.TransactionDTO

import io.swagger.client.infrastructure.*

class TransactionControllerApi(basePath: kotlin.String = "https://localhost:8443") : ApiClient(basePath) {

    /**
     * 
     * 
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun allTransaction(): kotlin.Any {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/transactions"
        )
        val response = request<kotlin.Any>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
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
     * @return TransactionDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun createTransaction(body: TransactionCreateDTO): TransactionDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/transactions"
        )
        val response = request<TransactionDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TransactionDTO
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
    fun deleteTransaction(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/transactions/{id}".replace("{" + "id" + "}", "$id")
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
     * @return TransactionDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun replaceTransaction(body: TransactionDTO, id: kotlin.String): TransactionDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/transactions/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<TransactionDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TransactionDTO
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
     * @return TransactionDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun transactionById(id: kotlin.String): TransactionDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/transactions/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<TransactionDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TransactionDTO
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
     * @return TransactionDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun updateTransaction(body: TransactionDTO, id: kotlin.String): TransactionDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PATCH,
                "/api/v1/transactions/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<TransactionDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TransactionDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
