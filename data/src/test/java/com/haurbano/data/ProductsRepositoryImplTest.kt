package com.haurbano.data

import com.haurbano.testing_commons.UnitTest
import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.data.repositories.ProductsRepositoryImpl
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.lang.IllegalStateException

class ProductsRepositoryImplTest : UnitTest() {

    @Mock
    lateinit var remoteDataSource: ProductsRemoteDataSource

    private lateinit var productsRepositoryImpl: ProductsRepositoryImpl

    @Before
    fun setup() {
        productsRepositoryImpl = ProductsRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `searchProductsBy and the answer is a list of ProductPreviews`() = runBlocking {
        // Given
        val query = "Pixel 4"
        val listProductPreview = mocksFactory.createListOf(
            clazz = ProductPreview::class.java,
            listSize = 2
        )
        `when`(remoteDataSource.searchProductsBy(query)).thenReturn(listProductPreview)

        // When
        val response = productsRepositoryImpl.searchProductsBy(query)

        // Then
        assert(response.size == 2)
    }

    @Test(expected = IllegalStateException::class)
    fun `searchProductsBy and an error occurs`() = runBlocking {
        // Given
        val query = "Pixel 4"
        `when`(remoteDataSource.searchProductsBy(query)).thenThrow(IllegalStateException())

        // When
        val response = productsRepositoryImpl.searchProductsBy(query)

        // Then
        // Error should be throw
    }

    @Test
    fun `getProductDetails and the answer is a ProductDetails`() = runBlocking {
        // Given
        val productId = "FakeID"
        val productDetails = mocksFactory.createObject(ProductDetails::class.java)
        `when`(remoteDataSource.getProductDetails(productId)).thenReturn(productDetails)

        // When
        val answer = productsRepositoryImpl.getProductDetails(productId)

        // Then
        assert(answer == productDetails)
    }

    @Test(expected = IllegalStateException::class)
    fun `getProductDetails and an error occurs`() = runBlocking {
        // Given
        val productId = "FakeID"
        `when`(remoteDataSource.getProductDetails(productId)).thenThrow(IllegalStateException())

        // When
        val answer = productsRepositoryImpl.getProductDetails(productId)

        // Then
        // Error should be throw
    }
}