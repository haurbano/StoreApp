package com.haurbano.remotedatasource.models

data class FeaturesAndDescriptionResponse(
    val id: String,
    val short_description: ShortDescription?,
    val main_features: List<Feature>
) {
    data class Feature(
        val text: String,
        val type: String
    )

    data class ShortDescription(
        val type: String?,
        val content: String?
    )
}