package com.enterprise.android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.enterprise.android_app.controller.RefreshInterceptor
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view.PageApp
import okhttp3.OkHttpClient


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //gfgHttpClient()
        CurrentDataUtils._application = this.application
        setContent {
            AndroidappTheme {
                PageApp(this)
            }
        }
    }

}


fun gfgHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(RefreshInterceptor())
    return builder.build()
}


