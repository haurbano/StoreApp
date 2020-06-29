package com.haurbano.domain

import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.respositories.ProductsRepository
import com.haurbano.domain.usecases.GetProductDetailsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.lang.IllegalStateException

class GetProductDetailUseCaseTest : UnitTest(){

    @Mock
    lateinit var productsRepository: ProductsRepository

    lateinit var getProductsUseCase: GetProductDetailsUseCase
    private val productDetail = ProductDetails(
        availableQuantity = 2,
        attributes = emptyList(),
        condition = "New",
        id = "23jask323",
        title = "Mock Product",
        soldQuantity = 3,
        warranty = "1 Year",
        currencyId = "COP",
        images = emptyList(),
        price = 32000
    )

    @Before
    fun setup() {
        getProductsUseCase = GetProductDetailsUseCase(productsRepository)
    }

    @Test
    fun `UseCase invoked and the repository return a lis of ProductReview`(): Unit = runBlocking {
        // Given
        val productId = "MockID"
        `when`(productsRepository.getProductDetails(productId)).thenReturn(productDetail)

        // When
        val responseProductDetail = getProductsUseCase(productId)

        // Then
        assert(responseProductDetail == productDetail)
    }

    @Test(expected = IllegalStateException::class)
    fun `UseCase invoked and a error occurs`() = runBlocking {
        // Given
        val productId = "MockID"
        `when`(productsRepository.getProductDetails(productId)).thenThrow(IllegalStateException())

        // When
        val responseProductDetail = getProductsUseCase(productId)
    }
}