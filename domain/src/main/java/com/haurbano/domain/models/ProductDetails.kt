package com.haurbano.domain.models

data class ProductDetails(
    val availableQuantity: Int,
    val condition: String,
    val currencyId: String,
    val id: String,
    val price: Float,
    val soldQuantity: Int,
    val title: String,
    val images: List<String>,
    val warranty: String?,
    var description: String? = "",
    var features: List<ProductFeature> = emptyList()
)