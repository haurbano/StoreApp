package com.haurbano.remotedatasource.models

import com.haurbano.domain.models.ProductAttribute

data class GetProductResponse(
    val attributes: List<ProductAttribute>,
    val available_quantity: Int,
    val base_price: Int,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val descriptions: List<Any>,
    val id: String,
    val initial_quantity: Int,
    val original_price: Int,
    val permalink: String,
    val pictures: List<ProductPicture>,
    val price: Int,
    val secure_thumbnail: String,
    val sold_quantity: Int,
    val thumbnail: String,
    val title: String,
    val warranty: String
) {
    class Geolocation(
    )

    class Location(
    )

    class SellerAddress(
    )

    class Shipping(
    )
}