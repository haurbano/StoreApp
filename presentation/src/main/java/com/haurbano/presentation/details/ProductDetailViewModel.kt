package com.haurbano.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haurbano.domain.common.Resource
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.usecases.GetProductDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : ViewModel() {

    private val productLiveData = MutableLiveData<Resource<ProductDetails>>()

    fun getProduct(): LiveData<Resource<ProductDetails>> = productLiveData

    fun fetchProductDetails(productId: String) {
        productLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val productDetails = getProductDetailsUseCase(productId)
                    productLiveData.postValue(Resource.success(productDetails))
                } catch (exc: Exception) {
                    productLiveData.postValue(Resource.error(message = "Something was wrong"))
                }
            }
        }
    }
}