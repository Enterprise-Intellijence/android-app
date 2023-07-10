package com.enterprise.android_app.view.screen.filter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view.SingleRowTemplate
import com.enterprise.android_app.view_models.SearchPageViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Check
import io.swagger.client.models.Product
import io.swagger.client.models.ProductDTO

@Composable
fun ConditionPage( viewModel: SearchPageViewModel, onApply: () -> Unit) {

    val conditions = listOf<Condition>( Condition.NEW_WITH_TAG, Condition.NEW_WITHOUT_TAG, Condition.VERY_GOOD, Condition.GOOD, Condition.ACCEPTABLE)

    val lazyColumnState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn( state = lazyColumnState ){
                items(conditions.size) { index ->
                    val condition = conditions[index]
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = viewModel.selectedCondition.contains(condition),
                            onCheckedChange = { checked_ ->
                                if (checked_) {
                                    viewModel.selectedCondition.add(condition)
                                } else {
                                    viewModel.selectedCondition.remove(condition)
                                    
                                }
                            }
                        )
                        Text(
                            modifier = Modifier.padding(start = 3.dp),
                            text = condition.value
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = CircleShape,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(13.dp),
            onClick = {
                viewModel.filter.value.condition = viewModel.selectedCondition.stream().toArray { arrayOfNulls<Condition>(it) }
                onApply()
            }
        ) {
            Icon(
                FontAwesomeIcons.Solid.Check,
                contentDescription = stringResource(id = R.string.apply),
                Modifier.size(20.dp)
            )
        }
    }
}

enum class Condition(val value: kotlin.String){
    NEW_WITH_TAG("New with tag"),
    NEW_WITHOUT_TAG("New without tag"),
    VERY_GOOD("Very good"),
    GOOD("Good"),
    ACCEPTABLE("Acceptable");
}