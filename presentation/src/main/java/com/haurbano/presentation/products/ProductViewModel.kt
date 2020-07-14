package com.haurbano.presentation.products

import androidx.lifecycle.*
import com.haurbano.domain.common.Resource
import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val productsLiveData = MutableLiveData<Resource<List<ProductPreview>>>()
    fun getProducts(): LiveData<Resource<List<ProductPreview>>> = productsLiveData

    fun searchBy(query: String) {
        viewModelScope.launch {
            productsLiveData.postValue(Resource.loading())
            val products = searchProductsUseCase(query)
            productsLiveData.postValue(products)
        }
    }
}