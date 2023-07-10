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
import io.swagger.client.models.PageReviewDTO
import io.swagger.client.models.ReviewCreateDTO
import io.swagger.client.models.ReviewDTO

import io.swagger.client.infrastructure.*

class ReviewControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param userId  
     * @param page  (optional, default to 0)
     * @param sizePage  (optional, default to 10)
     * @return PageReviewDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun allReviewReceived(userId: kotlin.String, page: kotlin.Int? = null, sizePage: kotlin.Int? = null): PageReviewDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (page != null) {
                put("page", listOf(page.toString()))
            }
            if (sizePage != null) {
                put("sizePage", listOf(sizePage.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/reviews/{userId}/received".replace("{" + "userId" + "}", "$userId"), query = localVariableQuery
        )
        val response = request<PageReviewDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageReviewDTO
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
     * @param page  (optional, default to 0)
     * @param sizePage  (optional, default to 10)
     * @return PageReviewDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun allReviewSent(userId: kotlin.String, page: kotlin.Int? = null, sizePage: kotlin.Int? = null): PageReviewDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (page != null) {
                put("page", listOf(page.toString()))
            }
            if (sizePage != null) {
                put("sizePage", listOf(sizePage.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/reviews/{userId}/sent".replace("{" + "userId" + "}", "$userId"), query = localVariableQuery
        )
        val response = request<PageReviewDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageReviewDTO
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
     * @return ReviewDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun createReview(body: ReviewCreateDTO): ReviewDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/reviews"
        )
        val response = request<ReviewDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ReviewDTO
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
    fun deleteReview(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/reviews/{id}".replace("{" + "id" + "}", "$id")
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
     * @return ReviewDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun reviewById(id: kotlin.String): ReviewDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/reviews/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<ReviewDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ReviewDTO
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
     * @return ReviewDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun updateReview(body: ReviewDTO, id: kotlin.String): ReviewDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/reviews/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<ReviewDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ReviewDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
