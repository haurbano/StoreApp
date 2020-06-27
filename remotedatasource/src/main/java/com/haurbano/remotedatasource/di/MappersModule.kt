package com.haurbano.remotedatasource.di

import com.haurbano.remotedatasource.mappers.SearchResponseToProductListMapper
import org.koin.dsl.module

val mappersModule = module {
    factory { SearchResponseToProductListMapper() }
}