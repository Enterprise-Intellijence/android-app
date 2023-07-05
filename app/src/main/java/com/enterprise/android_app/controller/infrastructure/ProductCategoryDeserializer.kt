package com.enterprise.android_app.controller.infrastructure

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import io.swagger.client.models.ProductCategoryDTO
import java.lang.reflect.Type

class ProductCategoryDeserializer: JsonDeserializer<ProductCategoryDTO> {
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): ProductCategoryDTO {
            val jsonObject = json.asJsonObject
            val id = jsonObject.get("id").asString
            val primaryCat = jsonObject.get("primaryCat").asString
            val secondaryCat = jsonObject.get("secondaryCat").asString
            val tertiaryCat = jsonObject.get("tertiaryCat").asString

            return ProductCategoryDTO(id, primaryCat, secondaryCat, tertiaryCat)
        }
}