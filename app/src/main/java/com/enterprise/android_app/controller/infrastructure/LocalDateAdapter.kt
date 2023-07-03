package io.swagger.client.infrastructure

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter {

    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    @ToJson
    fun toJson(value: LocalDate): String {
        return value.format(formatter)
    }

    @FromJson
    fun fromJson(value: String): LocalDate {
        return LocalDate.parse(value, formatter)
    }
}