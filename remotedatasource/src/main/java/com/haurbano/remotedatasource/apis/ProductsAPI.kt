package com.haurbano.remotedatasource.apis

import com.haurbano.remotedatasource.urls.Urls
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {
    @GET(Urls.SEARCH)
    suspend fun searchProduct(
        @Path("query") query: String,
        @Path("siteId") siteId: String = "MCO"
    ): ProductsSearchResponse
}