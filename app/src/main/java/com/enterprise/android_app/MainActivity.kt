package com.enterprise.android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enterprise.android_app.controller.RefreshInterceptor
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.NavGraph
import com.enterprise.android_app.ui.theme.AndroidappTheme
import okhttp3.OkHttpClient


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //gfgHttpClient()
        CurrentDataUtils._application = this.application
        setContent {
            AndroidappTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }

}


fun gfgHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(RefreshInterceptor())
    return builder.build()
}


