package com.enterprise.android_app.view.screen.filter

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        val lazyColumnState = rememberLazyListState()
        LazyColumn(modifier = Modifier.fillMaxSize(), state= lazyColumnState) {
            item {
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
    SingleRowTemplate(name = stringResource(R.string.sort_by), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
       Router.navigateTo(Options.SortBy)
    }
    SingleRowTemplate(name = stringResource(R.string.category), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Category)
    }
    SingleRowTemplate(name = stringResource(R.string.price), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Price)
    }
    SingleRowTemplate(name = stringResource(R.string.size), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Size)
    }
    SingleRowTemplate(name = stringResource(R.string.color), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Color)
    }
    SingleRowTemplate(name = stringResource(id = R.string.condition), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Condition)
    }
    SingleRowTemplate(name = stringResource(id = R.string.brand), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Brand)
    }
    SingleRowTemplate(name = stringResource(id = R.string.material), icona = null, icon_label = null, modifier = Modifier.fillMaxWidth()) {
        Router.navigateTo(Options.Material)
    }
}

object Router{
    val currentPage: MutableState<Options> = mutableStateOf(Options.Root)

    fun navigateTo(destination: Options){
        currentPage.value = destination
    }
}