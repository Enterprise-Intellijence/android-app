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
package io.swagger.client.models

import io.swagger.client.models.User

/**
 * 
 * @param id 
 * @param description 
 * @param urlPhoto 
 * @param user 
 */
data class UserImage (

    val id: kotlin.String? = null,
    val description: kotlin.String? = null,
    val urlPhoto: kotlin.String? = null,
    val user: User? = null
) {
}