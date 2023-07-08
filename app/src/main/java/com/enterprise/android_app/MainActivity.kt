package com.enterprise.android_app

import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view.GoogleAuthUiClient
import com.enterprise.android_app.view.PageApp
import com.enterprise.android_app.view_models.AuthViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CurrentDataUtils._application = this.application

        setContent {
            AndroidappTheme {
                PageApp()
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


