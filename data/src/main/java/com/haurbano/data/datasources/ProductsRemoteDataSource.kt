package com.haurbano.data.datasources

import com.haurbano.domain.models.Product

interface ProductsRemoteDataSource {
    suspend fun searchProductsBy(query: String): List<Product>
}