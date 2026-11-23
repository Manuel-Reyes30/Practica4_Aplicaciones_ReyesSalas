package com.example.nav3sampies.ui.screens.detail

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.nav3sampies.ui.screens.detail.ProductDetail
import com.example.nav3sampies.ui.screens.detail.ProductDetailScreen
import com.example.nav3sampies.ui.screens.detail.ProductDetailViewModel
import com.example.nav3sampies.ui.screens.home.Home

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally()
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(),
                slideOutHorizontally(targetOffsetX = { it })
            )
        },
        sceneStrategy = rememberListDetailSceneStrategy(),
        entryProvider = entryProvider {
            entry<Home>(
                metadata = ListDetailSceneStrategy.listPane(
                    detailPlaceholder = {
                        Text(
                            text = "Choose a product from the list",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )
            ) {
                Home(
                    onProductClick = { productId ->
                        backStack.add(ProductDetail(productId))
                    }
                )
            }
            entry<ProductDetail>(
                metadata = ListDetailSceneStrategy.detailPane()
            ) { key ->
                ProductDetailScreen(
                    viewModel = viewModel { ProductDetailViewModel(key.productId) },
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}
