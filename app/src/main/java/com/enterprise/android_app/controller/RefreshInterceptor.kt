package com.enterprise.android_app.controller

import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.AppRouter
import com.enterprise.android_app.navigation.MainRouter
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.navigation.Screen
import io.swagger.client.apis.UserControllerApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class RefreshInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var accessToken = CurrentDataUtils.accessToken

        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))

        if (response.code == 407) {
            val newAccessToken = CurrentDataUtils.accessToken
            if (newAccessToken != accessToken) {
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            } else {
                refreshToken()
                accessToken = CurrentDataUtils.accessToken
                if (accessToken.isNullOrBlank()) {
                    AppRouter.navigateTo(Screen.StartScreen)
                    return response
                }
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            }
        }

        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

    private fun refreshToken() {
        synchronized(this) {

            CurrentDataUtils.refreshToken()
            }
        }
}

