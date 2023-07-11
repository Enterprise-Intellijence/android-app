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

import io.swagger.client.models.PageableObject
import io.swagger.client.models.SortObject
import io.swagger.client.models.UserBasicDTO

/**
 * 
 * @param totalPages 
 * @param totalElements 
 * @param first 
 * @param last 
 * @param size 
 * @param content 
 * @param number 
 * @param sort 
 * @param numberOfElements 
 * @param pageable 
 * @param empty 
 */
data class PageUserBasicDTO (

    val totalPages: kotlin.Int? = null,
    val totalElements: kotlin.Long? = null,
    val first: kotlin.Boolean? = null,
    val last: kotlin.Boolean? = null,
    val size: kotlin.Int? = null,
    val content: kotlin.Array<UserBasicDTO>? = null,
    val number: kotlin.Int? = null,
    val sort: SortObject? = null,
    val numberOfElements: kotlin.Int? = null,
    val pageable: PageableObject? = null,
    val empty: kotlin.Boolean? = null
) {
}