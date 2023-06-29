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

import io.swagger.client.models.UserBasicDTO

/**
 * 
 * @param id 
 * @param followingFrom 
 * @param follower 
 * @param following 
 */
data class FollowingFollowersDTO (

    val id: kotlin.String? = null,
    val followingFrom: java.time.LocalDateTime? = null,
    val follower: UserBasicDTO? = null,
    val following: UserBasicDTO? = null
) {
}