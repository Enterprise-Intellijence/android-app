package com.enterprise.android_app.view.screen.filter

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view_models.SearchPageViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Check

@Composable
fun PricePage( viewModel: SearchPageViewModel, onApply: () -> Unit) {

    val context = LocalContext.current
    val errorString = stringResource(id = R.string.price_range_error)

    var min by rememberSaveable { mutableStateOf("") }
    var max by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Row(Modifier.padding(all = 20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CommonTextField(
                    title = "Min",
                    placeholder = "min...",
                    text = min,
                    onValueChange = { min = it }
                )
                Spacer(modifier = Modifier.width(20.dp))
                CommonTextField(
                    title = "Max",
                    placeholder = "...max",
                    text = max,
                    onValueChange = { max = it }
                )
            }
        }
        FloatingActionButton(containerColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = CircleShape,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(13.dp),
            onClick = {
                if(min.isNotBlank() && max.isNotBlank() && min.toDouble() > max.toDouble()){
                    Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
                    return@FloatingActionButton
                }
                if (min.isNotBlank())
                    viewModel.filter.value.minProductCost = min.toDouble()
                if (max.isNotBlank())
                    viewModel.filter.value.maxProductCost = max.toDouble()
                onApply()
            }) {
            Icon(
                FontAwesomeIcons.Solid.Check,
                contentDescription = stringResource(id = R.string.apply),
                Modifier.size(20.dp)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    title: String,
    placeholder: String,
    text: String,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier.width(150.dp),
        label = {
            Text(text = title)
        },
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )

}
