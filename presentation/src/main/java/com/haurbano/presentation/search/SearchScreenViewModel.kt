package com.haurbano.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val searchScreenState = MutableStateFlow(
        SearchResultScreenUIState(
            isLoading = true,
            productsViews = emptyList(),
            search = ""
        )
    )

    val uiState: StateFlow<SearchResultScreenUIState> = searchScreenState.asStateFlow()

    fun searchBy(query: String) {
        viewModelScope.launch {
            val products = searchProductsUseCase(query)
            searchScreenState.update {
                it.copy(
                    isLoading = false,
                    productsViews = products.data ?: emptyList(),
                    search = query
                )
            }
        }
    }
}

data class SearchResultScreenUIState(
    val isLoading: Boolean = false,
    val search: String,
    val productsViews: List<ProductPreview>
)