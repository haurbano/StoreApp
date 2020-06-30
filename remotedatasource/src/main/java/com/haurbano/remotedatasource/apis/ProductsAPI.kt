package com.haurbano.remotedatasource.apis

import com.haurbano.remotedatasource.models.GetProductResponse
import com.haurbano.remotedatasource.urls.Urls
import com.haurbano.remotedatasource.models.ProductsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductsAPI {

    @GET(Urls.SEARCH)
    suspend fun searchProduct(
        @Path("siteId") siteId: String = Urls.DEFAULT_SITE,
        @Query("q") query: String
    ): ProductsSearchResponse

    @GET(Urls.GET_PRODUCT)
    suspend fun getProduct(@Path("productId") productId: String): GetProductResponse
}