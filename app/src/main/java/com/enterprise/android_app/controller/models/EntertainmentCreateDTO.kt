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

import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.ProductCategoryDTO
import io.swagger.client.models.ProductCreateDTO

/**
 * 
 * @param entertainmentLanguage 
 */
data class EntertainmentCreateDTO (

    val entertainmentLanguage: EntertainmentLanguage
) {
    /**
    * 
    * Values: MULTILANGUAGE,ITALIAN,FRENCH,GERMAN,ENGLISH,SPANISH,OTHERS
    */
    enum class EntertainmentLanguage(val value: kotlin.String){
        MULTILANGUAGE("MULTI_LANGUAGE"),
        ITALIAN("ITALIAN"),
        FRENCH("FRENCH"),
        GERMAN("GERMAN"),
        ENGLISH("ENGLISH"),
        SPANISH("SPANISH"),
        OTHERS("OTHERS");
    }
}