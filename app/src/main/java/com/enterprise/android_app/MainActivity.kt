package com.enterprise.android_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
//import com.enterprise.android_app.model.persistence.AppDatabase
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view.PageApp


class MainActivity : ComponentActivity() {
    //private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

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


        setContent {
            AndroidappTheme {
                PageApp()
            }
        }
    }
}


