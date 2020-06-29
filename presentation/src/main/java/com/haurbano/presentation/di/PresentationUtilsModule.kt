package com.haurbano.presentation.di

import com.haurbano.presentation.common.ErrorMessageProvider
import com.haurbano.presentation.common.ErrorMessageProviderImpl
import org.koin.dsl.module

val presentationUtilsModule = module {
    factory<ErrorMessageProvider> { ErrorMessageProviderImpl(get()) }
}