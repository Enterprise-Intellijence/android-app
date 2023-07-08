package com.enterprise.android_app.view.screen.filter

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.view.SingleRowTemplate
import com.enterprise.android_app.view_models.SearchPageViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Check

sealed class Options {
    object Root : Options()
    object SortBy : Options()
    object Category : Options()
    object Price : Options()
    object Size : Options()
    object Condition : Options()
}
@Composable
fun FilterScreen(viewModel: SearchPageViewModel, onApply: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxSize()) {
                when(Router.currentPage.value) {
                    Options.Root -> {
                        RootPage(viewModel ,onClear = { viewModel.clearFilter() }, onApply = onApply)
                    }

                    Options.SortBy -> {
                        SortByPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Category -> {
                        CategoryPage(viewModel = viewModel, onApply = {
                            viewModel.filter.value.sizes = null
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Price -> {
                        PricePage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Size -> {
                        SizePage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }


                    Options.Condition -> {
                        ConditionPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                }

        }
    BackHandler(enabled = true) {
        if (Router.currentPage.value == Options.Root) {
            onApply()
        }
        viewModel.counter = 0
        Router.navigateTo(Options.Root)
    }
}

@Composable
fun RootPage(viewModel: SearchPageViewModel,onClear: () -> Unit, onApply: () -> Unit){
    val scrollState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(state = scrollState, modifier = Modifier) {
            item {
                SingleRowTemplate(
                    name = stringResource(R.string.sort_by),
                    content = when(viewModel.filter.value.sortBy) {
                        "productCost.price" -> stringResource(R.string.price)
                        "uploadDate" -> stringResource(R.string.NEWEST_FIRST)
                        "views" -> stringResource(R.string.RELEVANCE)
                        else -> stringResource(R.string.RELEVANCE)
                    },
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.SortBy)
                }
            }
            item {
                val primaryCat = viewModel.filter.value.primaryCat?:"All"
                var secondaryCat = viewModel.filter.value.secondaryCat?:""


                SingleRowTemplate(
                    name = stringResource(R.string.category),
                    content = if(secondaryCat != "") {
                        "$primaryCat > $secondaryCat..."
                    } else {
                        primaryCat
                    },
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Category)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(R.string.price),
                    content = if(viewModel.filter.value.minProductCost == null && viewModel.filter.value.maxProductCost == null){
                        ""
                    }else{
                        "${viewModel.filter.value.minProductCost?:""} € - ${viewModel.filter.value.maxProductCost?:""} €"
                    },
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Price)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(R.string.size),
                    content = viewModel.filter.value.sizes?.joinToString(", ") ?: "",
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Size)
                }
            }

            item {
                SingleRowTemplate(
                    name = stringResource(id = R.string.condition),
                    content = viewModel.filter.value.condition,
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Condition)
                }
            }
        }
        FloatingActionButton(containerColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = CircleShape,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(13.dp),
            onClick = onApply) {
            Icon(
                FontAwesomeIcons.Solid.Check,
                contentDescription = stringResource(id = R.string.apply),
                Modifier.size(20.dp)
            )
        }

        FloatingActionButton(containerColor = MaterialTheme.colorScheme.errorContainer,
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .width(100.dp)
                .align(Alignment.BottomStart)
                .padding(13.dp),
            onClick = onClear) {
            Text(text = stringResource(id = R.string.clear))
        }
    }
}

object Router{
    val currentPage: MutableState<Options> = mutableStateOf(Options.Root)

    fun navigateTo(destination: Options){
        currentPage.value = destination
    }
}