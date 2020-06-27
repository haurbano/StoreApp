package com.haurbano.domain.models

data class ProductDetails(
    val availableQuantity: Int,
    val condition: String,
    val currencyId: String,
    val id: String,
    val price: Int,
    val soldQuantity: Int,
    val title: String,
    val images: List<String>,
    val attributes: List<ProductAttribute>,
    val warranty: String
)