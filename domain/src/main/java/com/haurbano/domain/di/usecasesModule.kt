package com.haurbano.domain.di

import com.haurbano.domain.usecases.SearchProductsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { SearchProductsUseCase(get()) }
}