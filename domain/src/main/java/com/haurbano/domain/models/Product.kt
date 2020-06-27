package com.haurbano.domain.models

data class Product(
    val availableQuantity: Int,
    val condition: String,
    val currencyId: String,
    val id: String,
    val price: Int,
    val soldQuantity: Int,
    val thumbnail: String,
    val title: String
)