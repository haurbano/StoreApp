package com.haurbano.data.di

import com.haurbano.data.repositories.ProductsRepositoryImpl
import com.haurbano.domain.respositories.ProductsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory <ProductsRepository> { ProductsRepositoryImpl(get()) }
}