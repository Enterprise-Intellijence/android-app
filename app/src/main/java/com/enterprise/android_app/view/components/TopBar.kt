package com.enterprise.android_app.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.navigation.MainRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch() = TopAppBar(
    title = {
        TextField(
            value = "",
            onValueChange = { /*TODO: Gestisci il valore del TextField*/ },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.search))
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    },
    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(start = 0.dp, end = 10.dp, top = 10.dp),

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarGeneric() = TopAppBar(
    title = {
        /*Text(text =  MainRouter.currentPage,style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))*/
    },
    navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = stringResource(id = R.string.back))
        }
    },
    actions = {
        Row {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Check, contentDescription = stringResource(id = R.string.apply))
            }
        }
    }




)