package com.haurbano.remotedatasource.models

class SearchResult (
    val accepts_mercadopago: Boolean,
    val address: Address,
    val attributes: List<Attribute>,
    val available_quantity: Int,
    val buying_mode: String,
    val catalog_product_id: Any,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val domain_id: String,
    val id: String,
    val installments: Installments,
    val listing_type_id: String,
    val official_store_id: Any,
    val original_price: Any,
    val permalink: String,
    val price: Int,
    val seller: Seller,
    val seller_address: SellerAddress,
    val shipping: Shipping,
    val site_id: String,
    val sold_quantity: Int,
    val stop_time: String,
    val tags: List<Any>,
    val thumbnail: String,
    val title: String
) {
    class Address

    class Attribute

    class Installments

    class Seller

    class SellerAddress

    class Shipping
}