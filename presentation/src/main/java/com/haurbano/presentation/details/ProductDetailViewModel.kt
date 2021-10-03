package com.haurbano.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.usecases.GetProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProductDetailsUIState(isLoading = true, productDetails = ProductDetails.empty())
    )
    val uiState: StateFlow<ProductDetailsUIState> = _uiState.asStateFlow()

    fun fetchProductDetails(productId: String) {
        viewModelScope.launch {
            val productDetails = getProductDetailsUseCase(productId)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    productDetails = productDetails.data?: ProductDetails.empty()
                )
            }
        }
    }
}

data class ProductDetailsUIState(
    val isLoading: Boolean = false,
    val productDetails: ProductDetails
)