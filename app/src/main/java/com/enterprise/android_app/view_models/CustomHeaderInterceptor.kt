package com.enterprise.android_app.view_models

import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor(private val headerName: String, private val headerValue: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequestBuilder = originalRequest.newBuilder()
            .header(headerName, "Bearer $headerValue") // Inserisci il bearer token nell'header
            .method(originalRequest.method, originalRequest.body)

        val newRequest = newRequestBuilder.build()

        return chain.proceed(newRequest)
    }
}