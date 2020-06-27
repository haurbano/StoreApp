package com.haurbano.remotedatasource.products

import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.domain.models.Product
import com.haurbano.remotedatasource.apis.ProductsAPI
import com.haurbano.remotedatasource.mappers.SearchResponseToProductListMapper

class ProductsRemoteDataSourceImpl(
    private val productsAPI: ProductsAPI,
    private val mapper: SearchResponseToProductListMapper
) : ProductsRemoteDataSource {

    override suspend fun searchProductsBy(query: String): List<Product> {
        val response = productsAPI.searchProduct(query = query)
        return mapper(response)
    }
}