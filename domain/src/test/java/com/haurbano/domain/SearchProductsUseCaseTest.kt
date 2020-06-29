package com.haurbano.domain

import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.respositories.ProductsRepository
import com.haurbano.domain.usecases.SearchProductsUseCase
import com.haurbano.testing_commons.MocksFactory
import com.haurbano.testing_commons.UnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.lang.IllegalStateException

class SearchProductsUseCaseTest : UnitTest() {

    @Mock
    lateinit var productsRepository: ProductsRepository

    lateinit var searchProductsUseCase: SearchProductsUseCase
    private val productPreviewList = mocksFactory.createListOf(ProductPreview::class.java, 3)

    @Before
    fun setup() {
        searchProductsUseCase = SearchProductsUseCase(productsRepository)
    }

    @Test
    fun `UseCase invoked and the repository return a list of ProductPreview`(): Unit = runBlocking {
        // Given
        val query = "Pixel 3"
        `when`(productsRepository.searchProductsBy(query)).thenReturn(productPreviewList)

        // When
        val response = searchProductsUseCase(query)

        // Then
        assert(response.size == 3)
    }

    @Test(expected = IllegalStateException::class)
    fun `UseCase invoked and an error occurs `() = runBlocking {
        // Given
        val query = "Pixel 3"
        val errorMessage = "Internet problem"
        `when`(productsRepository.searchProductsBy(query)).thenThrow(
            IllegalStateException(errorMessage)
        )

        // When
        val response = searchProductsUseCase(query)
    }
}