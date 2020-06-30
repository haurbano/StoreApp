package com.haurbano.testing_commons

import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview
import java.lang.IllegalArgumentException

class MocksFactory {
    fun <T> createObject(clazz: Class<T>): T {
        return when (clazz) {
            ProductPreview::class.java -> getMockProductPreview() as T
            ProductDetails::class.java -> getMockProductDetail() as T
            else -> throw IllegalArgumentException("Class nor available to mock")
        }
    }

    fun <T> createListOf(clazz: Class<T>, listSize: Int): List<T> {
        return when (clazz) {
            ProductPreview::class.java -> getMockProductPreviewList(listSize) as List<T>
            else -> throw IllegalArgumentException("Class nor available to mock")
        }
    }

    private fun getMockProductPreview(): ProductPreview {
        return ProductPreview("New",
            "COP",
            "asf",
            99000f,
            "fake URL",
            "FakeProduct"
        )
    }

    private fun getMockProductDetail() = ProductDetails(
        availableQuantity = 2,
        condition = "New",
        id = "23jask323",
        title = "Mock Product",
        soldQuantity = 3,
        warranty = "1 Year",
        currencyId = "COP",
        images = emptyList(),
        price = 32000f
    )

    private fun getMockProductPreviewList(size: Int): List<ProductPreview> {
        val list = ArrayList<ProductPreview>()
        for (i in 0 until size) {
            list.add(getMockProductPreview())
        }
        return list
    }
}