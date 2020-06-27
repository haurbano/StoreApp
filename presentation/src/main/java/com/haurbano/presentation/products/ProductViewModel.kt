package com.haurbano.presentation.products

import androidx.lifecycle.*
import com.haurbano.domain.common.Resource
import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val productsLiveData = MutableLiveData<Resource<List<ProductPreview>>>()
    fun getProducts(): LiveData<Resource<List<ProductPreview>>> = productsLiveData

    fun searchBy(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productsLiveData.postValue(Resource.loading())
                try {
                    val products = searchProductsUseCase(query)
                    productsLiveData.postValue(Resource.success(products))
                } catch (exc: Exception) {
                    productsLiveData.postValue(Resource.error(message = "There was a problem"))
                }
            }
        }
    }
}