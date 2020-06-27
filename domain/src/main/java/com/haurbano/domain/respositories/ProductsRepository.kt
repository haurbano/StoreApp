package com.haurbano.domain.respositories

import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview

interface ProductsRepository {
    suspend fun searchProductsBy(query: String): List<ProductPreview>
    suspend fun getProductDetails(productId: String): ProductDetails
}