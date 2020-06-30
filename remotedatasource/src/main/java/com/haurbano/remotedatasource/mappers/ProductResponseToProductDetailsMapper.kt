package com.haurbano.remotedatasource.mappers

import com.haurbano.domain.models.ProductDetails
import com.haurbano.remotedatasource.models.GetProductResponse

class ProductResponseToProductDetailsMapper {
    operator fun invoke(response: GetProductResponse): ProductDetails {
        return ProductDetails(
            availableQuantity = response.available_quantity,
            id = response.id,
            currencyId = response.currency_id,
            condition = response.condition,
            soldQuantity = response.sold_quantity,
            title = response.title,
            price = response.price,
            images = response.pictures.map { it.secure_url },
            warranty = response.warranty
        )
    }
}