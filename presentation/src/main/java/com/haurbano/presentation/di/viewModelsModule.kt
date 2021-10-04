package com.haurbano.presentation.di

import com.haurbano.presentation.details.ProductDetailViewModel
import com.haurbano.presentation.search.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SearchScreenViewModel(get()) }
    viewModel { ProductDetailViewModel(get()) }
}