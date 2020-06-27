package com.haurbano.presentation.di

import com.haurbano.presentation.products.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ProductViewModel(get()) }
}