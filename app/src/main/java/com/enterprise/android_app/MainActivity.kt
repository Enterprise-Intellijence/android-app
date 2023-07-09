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
import com.enterprise.android_app.view.PageApp
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


    /*
        if (false) {
            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            // Set the flag to indicate that the database has been created
            val editor = sharedPreferences.edit()
            editor.putBoolean("isFirstRun", false)
            editor.apply()

            Log.println(Log.ASSERT, "ciao", "dbcreato")
        } else {
            Log.println(Log.ASSERT, "ciao", "dbGIAcreato")
        }
        */
}


fun gfgHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(RefreshInterceptor())
    return builder.build()
}


