package com.haurbano.domain.respositories

import com.haurbano.domain.common.Resource
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview

interface ProductsRepository {
    suspend fun searchProductsBy(query: String): Resource<List<ProductPreview>>
    suspend fun getProductDetails(productId: String): Resource<ProductDetails>
}