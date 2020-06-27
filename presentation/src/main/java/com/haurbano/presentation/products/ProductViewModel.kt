package com.haurbano.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.haurbano.domain.common.Resource
import com.haurbano.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.Dispatchers

class ProductViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    fun searchBy(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            val products = searchProductsUseCase(query)
            emit(Resource.success(products))
        } catch (exc: Exception) {
            emit(Resource.error(message = "There was a problem"))
        }
    }
}