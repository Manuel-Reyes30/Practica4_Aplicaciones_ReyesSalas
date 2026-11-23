package com.example.nav3sampies.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nav3sampies.data.Product
import com.example.nav3sampies.data.products
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductDetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProductDetailViewModel(
    productId: Int,
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailState())
    val state: StateFlow<ProductDetailState> = _state.asStateFlow()

    init {
        loadProduct(productId)
    }

    private fun loadProduct(productId: Int) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                delay(500)

                val product = products.find { it.id == productId }

                if (product != null) {
                    _state.update {
                        it.copy(
                            product = product,
                            isLoading = false
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Product not found"
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error loading product: ${e.message}"
                    )
                }
            }
        }
    }
}
