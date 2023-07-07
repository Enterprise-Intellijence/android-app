package com.enterprise.android_app.view.screen.filter

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.controller.models.FilterOptions
import com.enterprise.android_app.navigation.Screen
import com.enterprise.android_app.view.SingleRowTemplate
import com.enterprise.android_app.view_models.SearchPageViewModel

sealed class Options {
    object Root : Options()
    object SortBy : Options()
    object Category : Options()
    object Price : Options()
    object Size : Options()
    object Color : Options()
    object Condition : Options()
    object Brand : Options()
    object Material : Options()
}
@Composable
fun FilterScreen(viewModel: SearchPageViewModel, onApply: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
                when(Router.currentPage.value) {
                    Options.Root -> {
                        RootPage(onApply = onApply)
                    }

                    Options.SortBy -> {
                        SortByPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Category -> {
                        CategoryPage(viewModel = viewModel, onApply = {
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

                    Options.Color -> {
                        ColorPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Condition -> {
                        ConditionPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Brand -> {
                        BrandPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }

                    Options.Material -> {
                        MaterialPage(viewModel = viewModel, onApply = {
                            Router.navigateTo(Options.Root)
                        })
                    }
                }

        }
    BackHandler(enabled = true) {
        if (Router.currentPage.value == Options.Root) {
            onApply()
        }
        Router.navigateTo(Options.Root)
    }
}

@Composable
fun RootPage(onApply: () -> Unit){
    val scrollState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
            item{
                SingleRowTemplate(
                    name = stringResource(R.string.sort_by),
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.SortBy)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(R.string.category),
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
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Size)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(R.string.color),
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Color)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(id = R.string.condition),
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Condition)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(id = R.string.brand),
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Brand)
                }
            }
            item {
                SingleRowTemplate(
                    name = stringResource(id = R.string.material),
                    icona = null,
                    icon_label = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Router.navigateTo(Options.Material)
                }
            }

        }
        
        Button(
            onClick = onApply,
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .padding(all = 20.dp).weight(1f),
            shape = RoundedCornerShape(7.dp),
        ) {
            Text(text = stringResource(id = R.string.show_results))
        }
    }
    
}

object Router{
    val currentPage: MutableState<Options> = mutableStateOf(Options.Root)

    fun navigateTo(destination: Options){
        currentPage.value = destination
    }
}