package com.haurbano.data.repositories

import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.respositories.ProductsRepository

class ProductsRepositoryImpl(
    private val remoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override suspend fun searchProductsBy(query: String): List<ProductPreview> {
        return remoteDataSource.searchProductsBy(query)
    }

    override suspend fun getProductDetails(productId: String): ProductDetails {
        return remoteDataSource.getProductDetails(productId)
    }
}