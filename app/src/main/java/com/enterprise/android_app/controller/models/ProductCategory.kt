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

import io.swagger.client.models.Product

/**
 * 
 * @param id 
 * @param primaryCat 
 * @param secondaryCat 
 * @param tertiaryCat 
 * @param visibility 
 * @param products 
 */
data class ProductCategory (

    val id: kotlin.String? = null,
    val primaryCat: kotlin.String? = null,
    val secondaryCat: kotlin.String? = null,
    val tertiaryCat: kotlin.String? = null,
    val visibility: Visibility? = null,
    val products: kotlin.Array<Product>? = null
) {
    /**
    * 
    * Values: PUBLIC,PRIVATE
    */
    enum class Visibility(val value: kotlin.String){
        PUBLIC("PUBLIC"),
        PRIVATE("PRIVATE");
    }
}