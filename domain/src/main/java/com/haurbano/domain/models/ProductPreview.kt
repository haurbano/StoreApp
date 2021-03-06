package com.haurbano.domain.models

data class ProductPreview(
    val condition: String,
    val currencyId: String,
    val id: String,
    val price: Float,
    val thumbnail: String,
    val title: String
)