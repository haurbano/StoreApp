package com.haurbano.remotedatasource.di

import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.remotedatasource.products.ProductsRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory <ProductsRemoteDataSource>{ ProductsRemoteDataSourceImpl(get(), get()) }
}