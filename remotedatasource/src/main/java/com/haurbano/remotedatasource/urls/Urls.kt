package com.haurbano.remotedatasource.urls

object Urls {
    const val BASE_URL = "https://api.mercadolibre.com/"
    const val SITE_ID_COLOMBIA = "MCO"

    const val SEARCH = "/sites/{{siteId}}/search?q={{query}}"
}