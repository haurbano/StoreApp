package com.haurbano.remotedatasource.di

import com.haurbano.remotedatasource.apis.ProductsAPI
import com.haurbano.remotedatasource.apis.RetrofitClient
import org.koin.dsl.module

val apisModule = module {
    single <ProductsAPI> { RetrofitClient.create(ProductsAPI::class.java) }
}