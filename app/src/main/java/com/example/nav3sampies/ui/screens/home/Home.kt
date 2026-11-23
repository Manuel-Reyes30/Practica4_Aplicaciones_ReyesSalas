package com.example.nav3sampies.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.example.nav3sampies.data.products
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(onProductClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(products) { product ->
                ListItem(
                    headlineContent = { Text(product.name) },
                    supportingContent = { Text("$${product.price}") },
                    modifier = Modifier.clickable {
                        onProductClick(product.id)
                    }
                )
            }
        }
    }
}
