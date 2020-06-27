package com.haurbano.remotedatasource.mappers

import com.haurbano.domain.models.ProductPreview
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import java.lang.StringBuilder

class SearchResponseToProductPreviewListMapper {
    operator fun invoke(response: ProductsSearchResponse): List<ProductPreview> {
        return response.results.map { product ->
            ProductPreview(
                price = product.price,
                title = product.title,
                thumbnail = getHttpsUrl(product.thumbnail),
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