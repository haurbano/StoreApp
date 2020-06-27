package com.haurbano.domain.respositories

import com.haurbano.domain.models.Product

interface ProductsRepository {
    suspend fun searchProductsBy(query: String): List<Product>
}