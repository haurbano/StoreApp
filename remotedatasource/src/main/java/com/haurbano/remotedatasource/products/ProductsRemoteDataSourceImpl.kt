package com.haurbano.remotedatasource.products

import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview
import com.haurbano.remotedatasource.apis.ProductsAPI
import com.haurbano.remotedatasource.mappers.ProductResponseToProductDetailsMapper
import com.haurbano.remotedatasource.mappers.SearchResponseToProductPreviewListMapper

class ProductsRemoteDataSourceImpl(
    private val productsAPI: ProductsAPI,
    private val searchToProductPreviewMapper: SearchResponseToProductPreviewListMapper,
    private val productResponseToProductDetailsMapper: ProductResponseToProductDetailsMapper
) : ProductsRemoteDataSource {

    override suspend fun searchProductsBy(query: String): List<ProductPreview> {
        val response = productsAPI.searchProduct(query = query)
        return searchToProductPreviewMapper(response)
    }

    override suspend fun getProductDetails(productId: String): ProductDetails {
        val response = productsAPI.getProduct(productId)
        return productResponseToProductDetailsMapper(response)
    }
}