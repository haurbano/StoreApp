package com.haurbano.remotedatasource.models

class SearchResult (
    val attributes: List<Attribute>,
    val available_quantity: Int,
    val buying_mode: String,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val id: String,
    val original_price: Any,
    val permalink: String,
    val price: Float,
    val seller: Seller,
    val sold_quantity: Int,
    val thumbnail: String,
    val title: String
) {
    class Address

    class Attribute

    class Seller (
        val id: String = "",
        val power_seller_status: String = ""
    )

}