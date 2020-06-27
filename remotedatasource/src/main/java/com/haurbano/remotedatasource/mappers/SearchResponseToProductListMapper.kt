package com.haurbano.remotedatasource.mappers

import com.haurbano.domain.models.Product
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import java.lang.StringBuilder

class SearchResponseToProductListMapper {
    operator fun invoke(response: ProductsSearchResponse): List<Product> {
        return response.results.map { product ->
            Product(
                availableQuantity = product.available_quantity,
                price = product.price,
                title = product.title,
                thumbnail = getHttpsUrl(product.thumbnail),
                soldQuantity = product.sold_quantity,
                condition = product.condition,
                currencyId = product.currency_id,
                id = product.id
            )
        }
    }

    private fun getHttpsUrl(url: String): String {
        return if (url.contains("https")) {
            url
        } else {
            StringBuilder(url).insert(4, "s").toString()
        }
    }
}