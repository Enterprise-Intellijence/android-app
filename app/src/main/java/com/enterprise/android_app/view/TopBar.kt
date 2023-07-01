package com.enterprise.android_app.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.enterprise.android_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch() = TopAppBar(
    title = { Text(text = stringResource(id = R.string.app_name))},
    navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = stringResource(id = R.string.back))
    } },
    actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Settings,contentDescription = stringResource(id = R.string.settings))

        }
    }

)

@Composable
fun TopBarGeneric(){}