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

/**
 * 
 * @param entertainmentLanguage 
 */
class EntertainmentCreateDTO: ProductCreateDTO {
    constructor(
        title: kotlin.String? = null,
        description: kotlin.String? = null,
        productCost: CustomMoneyDTO,
        deliveryCost: CustomMoneyDTO,
        brand: kotlin.String? = null,
        condition: ProductCreateDTO.Condition? = null,
        productSize: ProductCreateDTO.ProductSize? = null,
        visibility: ProductCreateDTO.Visibility? = null,
        productCategory: ProductCategoryDTO? = null,
        productImages: kotlin.Array<kotlin.Array<kotlin.Byte>>? = null,
        type: kotlin.String,
        entertainmentLanguage: EntertainmentLanguage
        ): super(
        title,
        description,
        productCost,
        deliveryCost,
        brand,
        condition,
        productSize,
        visibility,
        productCategory,
        productImages,
        type,
    )

    constructor(productCreateDTO: ProductCreateDTO,
                entertainmentLanguage: EntertainmentLanguage
    ): super(
        title = productCreateDTO.title,
        description = productCreateDTO.description,
        productCost = productCreateDTO.productCost,
        deliveryCost = productCreateDTO.deliveryCost,
        brand = productCreateDTO.brand,
        condition = productCreateDTO.condition,
        productSize = productCreateDTO.productSize,
        visibility = productCreateDTO.visibility,
        productCategory = productCreateDTO.productCategory,
        productImages = productCreateDTO.productImages,
        type = productCreateDTO.type,
    )
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