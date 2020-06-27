package com.haurbano.remotedatasource.apis

import com.haurbano.remotedatasource.urls.Urls
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsAPI {

    @GET(Urls.SEARCH)
    suspend fun searchProduct(
        @Path("siteId") siteId: String = "MCO",
        @Query("q") query: String
    ): ProductsSearchResponse
}