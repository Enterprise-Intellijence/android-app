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
import io.swagger.client.infrastructure.*
import io.swagger.client.models.PhotoprofileIdBody
import io.swagger.client.models.ProductIdBody
import io.swagger.client.models.ProductImageDTO
import io.swagger.client.models.UserImageDTO
import io.swagger.client.models.UsersPhotoprofileBody
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part


class ImageControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun deleteImageProduct(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/images/product/{id}".replace("{" + "id" + "}", "$id")
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
    fun deletePhotoUser(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/images/users/photo-profile/{id}".replace("{" + "id" + "}", "$id")
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
     * @param type  
     * @param folderName  
     * @param fileName  
     * @return kotlin.Array<kotlin.Byte>
     */
    @Suppress("UNCHECKED_CAST")
    fun getImage(type: kotlin.String, folderName: kotlin.String, fileName: kotlin.String): kotlin.Array<kotlin.Byte> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/images/{type}/{folder_name}/{file_name}".replace("{" + "type" + "}", "$type").replace("{" + "folder_name" + "}", "$folderName").replace("{" + "file_name" + "}", "$fileName")
        )
        val response = request<kotlin.Array<kotlin.Byte>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<kotlin.Byte>
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
     * @return void
     */
    fun replaceImageProduct(body: ProductIdBody, id: kotlin.String): Unit {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/images/product/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig, localVariableBody
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
     * @return void
     */
    fun replacePhotoUser(body: PhotoprofileIdBody, id: kotlin.String): Unit {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/images/users/photo-profile/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig, localVariableBody
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
     * @param productId  
     * @param description  
     * @return ProductImageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun saveImageProduct(image: MultipartBody, productId: String, description: String): ProductImageDTO {
        val localVariableBody: MultipartBody = image
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("product_id", listOf(productId.toString()))
            put("description", listOf(description.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/images/product", query = localVariableQuery
        )
        val response = request<ProductImageDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProductImageDTO
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
     * @param description  
     * @return UserImageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun savePhotoUser(body: UsersPhotoprofileBody, description: kotlin.String): UserImageDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("description", listOf(description.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/images/users/photo-profile", query = localVariableQuery
        )
        val response = request<UserImageDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as UserImageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
