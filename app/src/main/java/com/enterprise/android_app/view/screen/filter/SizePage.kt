package com.enterprise.android_app.view.screen.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.view.SingleRowTemplate
import com.enterprise.android_app.view_models.SearchPageViewModel
import com.enterprise.android_app.view_models.SizeViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Check
import kotlinx.coroutines.delay
import java.util.Arrays

@Composable
fun SizePage( viewModel: SearchPageViewModel, onApply: () -> Unit) {
    val sizeViewModel: SizeViewModel = SizeViewModel()

    var catList = listOf<String>("Accessories", "Cloths", "Bags", "Shoes", "Home")
    val sizes = sizeViewModel.sizesByCat.collectAsState()

    val showSizes = rememberSaveable { mutableStateOf(false) }

    val lazyColumnState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(Modifier) {
            Column(
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()) {
                    LazyColumn(state = lazyColumnState) {
                        if(!showSizes.value){
                            for (category in catList) {
                                item {
                                    SingleRowTemplate(name = category, icona = null, icon_label = null,
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            sizeViewModel.getSizesByCategory(category)
                                            viewModel.currentCat.value = category
                                            showSizes.value = true
                                        })
                                }
                            }
                        }
                        else{
                            if (sizes.value.isEmpty()){
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                            else{
                                for (category in sizes.value) {
                                    item {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Checkbox(
                                                checked = viewModel.tempSelectedSize.contains(
                                                    category
                                                ),
                                                onCheckedChange = { checked_ ->
                                                    if (checked_) {
                                                        viewModel.tempSelectedSize.add(category)
                                                    } else {
                                                        viewModel.tempSelectedSize.remove(category)
                                                    }

                                                }
                                            )

                                            Text(
                                                modifier = Modifier.padding(start = 3.dp),
                                                text = category
                                            )
                                        }
                                    }
                                }
                            }
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
                if (viewModel.currentCat.value == "Home" && viewModel.filter.value.primaryCat != "Home"){
                    viewModel.filter.value.primaryCat = viewModel.currentCat.value
                    viewModel.filter.value.secondaryCat = null
                    viewModel.filter.value.tertiaryCat = null
                }else {
                    if (viewModel.currentCat.value != "Home" && viewModel.filter.value.primaryCat == "Clothing") {
                        if (viewModel.filter.value.secondaryCat != viewModel.currentCat.value) {
                            viewModel.filter.value.secondaryCat = viewModel.currentCat.value
                            viewModel.filter.value.tertiaryCat = null
                        }
                    }else{
                            viewModel.filter.value.primaryCat = "Clothing"
                            viewModel.filter.value.secondaryCat = viewModel.currentCat.value
                            viewModel.filter.value.tertiaryCat = null
                    }
                }
                viewModel.filter.value.sizes = viewModel.tempSelectedSize.stream().toArray { size -> arrayOfNulls<String>(size) }

                showSizes.value = false
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