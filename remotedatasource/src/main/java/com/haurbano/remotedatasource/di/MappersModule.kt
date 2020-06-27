package com.haurbano.remotedatasource.di


import com.haurbano.remotedatasource.mappers.ProductResponseToProductDetailsMapper
import com.haurbano.remotedatasource.mappers.SearchResponseToProductPreviewListMapper
import org.koin.dsl.module

val mappersModule = module {
    factory { SearchResponseToProductPreviewListMapper() }
    factory { ProductResponseToProductDetailsMapper() }
}