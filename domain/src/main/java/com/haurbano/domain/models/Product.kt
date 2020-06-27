package com.haurbano.domain.models

data class Product(
    val available_quantity: Int,
    val condition: String,
    val currency_id: String,
    val id: String,
    val price: Int,
    val sold_quantity: Int,
    val thumbnail: String,
    val title: String
)