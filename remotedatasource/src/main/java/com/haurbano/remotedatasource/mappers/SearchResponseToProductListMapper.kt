package com.haurbano.remotedatasource.mappers

import com.haurbano.domain.models.Product
import com.haurbano.remotedatasource.models.ProductsSearchResponse

class SearchResponseToProductListMapper {
    operator fun invoke(response: ProductsSearchResponse): List<Product> {
        return response.results.map { product ->
            Product(
                availableQuantity = product.available_quantity,
                price = product.price,
                title = product.title,
                thumbnail = product.thumbnail,
                soldQuantity = product.sold_quantity,
                condition = product.condition,
                currencyId = product.currency_id,
                id = product.id
            )
        }
    }
}