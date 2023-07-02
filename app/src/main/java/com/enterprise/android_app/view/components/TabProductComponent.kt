package com.enterprise.android_app.view.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column as Column


@Composable
fun TabComponent() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.White
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            ) {
                Text(text = "Tab 1")
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            ) {
                Text(text = "Tab 2")
            }
            Tab(
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 }
            ) {
                Text(text = "Tab 3")
            }
        }

        // Content based on selected tab
        when (selectedTabIndex) {
            0 -> {
                // Content for Tab 1
                // ...
            }
            1 -> {
                // Content for Tab 2
                // ...
            }
            2 -> {
                // Content for Tab 3
                // ...
            }
        }
    }
}
