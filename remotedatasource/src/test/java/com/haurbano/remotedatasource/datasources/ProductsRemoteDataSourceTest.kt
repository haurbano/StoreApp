package com.haurbano.remotedatasource.datasources

import com.example.android_testing_commons.AndroidUnitTest
import com.haurbano.domain.models.ProductDetails
import com.haurbano.remotedatasource.apis.ProductsAPI
import com.haurbano.remotedatasource.di.remoteDataSourceModule
import com.haurbano.remotedatasource.mappers.ProductResponseToProductDetailsMapper
import com.haurbano.remotedatasource.mappers.SearchResponseToProductPreviewListMapper
import com.haurbano.remotedatasource.models.GetProductResponse
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import com.haurbano.remotedatasource.products.ProductsRemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.lang.IllegalStateException

class ProductsRemoteDataSourceTest : AndroidUnitTest() {

    @Mock
    lateinit var productsApi: ProductsAPI

    private val productResponseToProductDetailsMapper = ProductResponseToProductDetailsMapper()
    private val searchToProductPreviewMapper = SearchResponseToProductPreviewListMapper()
    private lateinit var productsRemoteDataSource: ProductsRemoteDataSourceImpl

    @Before
    fun setup() {
        productsRemoteDataSource = ProductsRemoteDataSourceImpl(
            productsApi, searchToProductPreviewMapper, productResponseToProductDetailsMapper
        )
    }

    @Test
    fun `searchProductsBy and the answer is a list of ProductPreview`() = runBlocking {
        // Given
        val query = "Query"
        val mockResponse = androidMocksFactory.createObject(ProductsSearchResponse::class.java)
        `when`(productsApi.searchProduct("MCO", query)).thenReturn(mockResponse)

        // When
        val response = productsRemoteDataSource.searchProductsBy("Query")

        // Then
        val mappedMockResponse = searchToProductPreviewMapper(mockResponse)
        assert(mappedMockResponse == response)
    }

    @Test
    fun `getProductDetails and the answer is a correct ProductDetails`() = runBlocking {
        // Given
        val productId = "FakeId"
        val mockResponse = androidMocksFactory.createObject(GetProductResponse::class.java)
        `when`(productsApi.getProduct(productId)).thenReturn(mockResponse)

        // When
        val response = productsRemoteDataSource.getProductDetails(productId)

        // Then
        val mappedMockResponse = productResponseToProductDetailsMapper(mockResponse)
        assert(mappedMockResponse == response)
    }

    @Test(expected = IllegalStateException::class)
    fun `searchProductsBy and an error occurs`() = runBlocking {
        val query = "Query"
        `when`(productsApi.searchProduct("MCO", query)).thenThrow(IllegalStateException())

        // Then
        val response = productsRemoteDataSource.searchProductsBy("Query")
    }

    @Test(expected = IllegalStateException::class)
    fun `getProductDetails and an error occurs`() = runBlocking {
        // Given
        val productId = "FakeId"
        `when`(productsApi.getProduct(productId)).thenThrow(IllegalStateException())

        // When
        val response = productsRemoteDataSource.getProductDetails(productId)
    }

}