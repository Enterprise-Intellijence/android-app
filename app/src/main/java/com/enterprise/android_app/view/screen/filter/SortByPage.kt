package com.enterprise.android_app.view.screen.filter

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view.components.LazyGridProductsCard
import com.enterprise.android_app.view_models.SearchPageViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Check
import compose.icons.fontawesomeicons.solid.Filter

@Composable
fun SortByPage( viewModel: SearchPageViewModel, onApply: () -> Unit) {


    val radioOptions = listOf(SortByOptions.RELEVANCE,
                            SortByOptions.PRICE_HIGH_TO_LOW, SortByOptions.PRICE_LOW_TO_HIGH,
                        SortByOptions.NEWEST_FIRST)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(
        when(viewModel.filter.value.sortBy){
            "views" -> SortByOptions.RELEVANCE
            "productCost.price" -> {
                if (viewModel.filter.value.sortDirection == "ASC")
                    SortByOptions.PRICE_LOW_TO_HIGH
                else
                    SortByOptions.PRICE_HIGH_TO_LOW
            }
            "uploadDate" -> SortByOptions.NEWEST_FIRST
            else -> {
                SortByOptions.RELEVANCE
            }
        }
    ) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = stringResource(id = R.string.sort_by),
                fontSize = 26.sp,
                modifier = Modifier.padding(all = Dp(value = 16F))
            )
            Column {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                            .padding(horizontal = 16.dp)
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            modifier = Modifier.padding(all = Dp(value = 8F)),
                            onClick = {

                                onOptionSelected(text)

                            }
                        )
                        Text(
                            text = text.value,
                            modifier = Modifier.padding(
                                start = Dp(value = (16F)),
                                top = Dp(value = (20F))
                            ),
                        )
                    }

                }

            }
        }
        FloatingActionButton(containerColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = CircleShape,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(13.dp),
            onClick = {
                when (selectedOption) {
                    SortByOptions.RELEVANCE -> {
                        viewModel.filter.value.sortBy = "views"
                        viewModel.filter.value.sortDirection = "DESC"
                    }

                    SortByOptions.PRICE_HIGH_TO_LOW -> {
                        viewModel.filter.value.sortBy = "productCost.price"
                        viewModel.filter.value.sortDirection = "DESC"
                    }

                    SortByOptions.PRICE_LOW_TO_HIGH -> {
                        viewModel.filter.value.sortBy = "productCost.price"
                        viewModel.filter.value.sortDirection = "ASC"
                    }

                    SortByOptions.NEWEST_FIRST -> {
                        viewModel.filter.value.sortBy = "uploadDate"
                        viewModel.filter.value.sortDirection = "DESC"
                    }
                }

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

enum class SortByOptions(val value: kotlin.String){
    RELEVANCE("Relevance"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    NEWEST_FIRST("Newest First");
}