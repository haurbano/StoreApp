package com.haurbano.remotedatasource.mappers

import com.example.android_testing_commons.AndroidUnitTest
import com.haurbano.domain.models.ProductDetails
import com.haurbano.remotedatasource.models.GetProductResponse
import org.junit.Before
import org.junit.Test

class ProductResponseToProductDetailsMapperTest : AndroidUnitTest() {

    lateinit var productResponseToProductDetailsMapper: ProductResponseToProductDetailsMapper

    @Before
    fun setup() {
        productResponseToProductDetailsMapper = ProductResponseToProductDetailsMapper()
    }

    @Test
    fun `Mapper invoked`() {
        // Given
        val serverResponse = androidMocksFactory.createObject(GetProductResponse::class.java)

        // When
        val mappedResponse = productResponseToProductDetailsMapper(serverResponse)

        // Then
        assert(getExpectedMappedResponse() == mappedResponse)

    }

    private fun getExpectedMappedResponse(): ProductDetails {
        return ProductDetails(
            availableQuantity = 3,
            attributes = emptyList(),
            price = 30000,
            title = "My Mock Product",
            id = "id",
            condition = "new",
            warranty = "1 Year",
            images = emptyList(),
            currencyId = "COP",
            soldQuantity = 23
        )
    }
}