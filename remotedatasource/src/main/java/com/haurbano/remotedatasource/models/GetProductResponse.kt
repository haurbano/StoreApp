package com.haurbano.remotedatasource.models

data class GetProductResponse(
    val available_quantity: Int,
    val base_price: Float,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val id: String,
    val initial_quantity: Int,
    val original_price: Int,
    val permalink: String,
    val pictures: List<ProductPicture>,
    val price: Float,
    val secure_thumbnail: String,
    val sold_quantity: Int,
    val thumbnail: String,
    val title: String,
    val warranty: String?,
    val catalog_product_id: String?
)