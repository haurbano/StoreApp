package com.haurbano.domain.models

data class ProductAttribute(
    val attribute_group_id: String,
    val attribute_group_name: String,
    val id: String,
    val name: String,
    val value_id: Any,
    val value_name: String
)