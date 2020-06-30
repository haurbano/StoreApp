package com.haurbano.remotedatasource.mappers

import com.example.android_testing_commons.AndroidUnitTest
import com.haurbano.domain.models.ProductPreview
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchResponseToProductListMapperTest : AndroidUnitTest() {
    lateinit var searchResponseToProductPreviewListMapper: SearchResponseToProductPreviewListMapper

    @Before
    fun setup() {
        searchResponseToProductPreviewListMapper = SearchResponseToProductPreviewListMapper()
    }

    @Test
    fun `Mapper invoked`() {
        // Given
        val serverResponse = androidMocksFactory.createObject(ProductsSearchResponse::class.java)
        searchResponseToProductPreviewListMapper(serverResponse)

        // When
        val mappedResponse = searchResponseToProductPreviewListMapper(serverResponse)

        // Then
        val expectedMapped = getExpectedMapped()
        for (product in mappedResponse) {
            assert(product.thumbnail.contains("https"))
        }
         Assert.assertArrayEquals(expectedMapped.toTypedArray(), mappedResponse.toTypedArray())
    }

    private fun getExpectedMapped(): List<ProductPreview> {
        return listOf(
            ProductPreview(
                "New",
                "COP",
                "id",
                23000f,
                "https:url",
                "My product"
            ),
            ProductPreview(
                "New",
                "COP",
                "id2",
                24000f,
                "https:url",
                "My product 2"
            )
        )
    }
}