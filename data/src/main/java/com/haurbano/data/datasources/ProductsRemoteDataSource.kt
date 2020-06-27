package com.haurbano.data.datasources

import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview

interface ProductsRemoteDataSource {
    suspend fun searchProductsBy(query: String): List<ProductPreview>
    suspend fun getProductDetails(productId: String): ProductDetails
}