package com.haurbano.data.repositories

import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.domain.models.Product
import com.haurbano.domain.respositories.ProductsRepository

class ProductsRepositoryImpl(
    private val remoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override suspend fun searchProductsBy(query: String): List<Product> {
        return remoteDataSource.searchProductsBy(query)
    }
}