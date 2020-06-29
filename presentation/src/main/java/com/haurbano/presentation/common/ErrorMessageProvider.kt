package com.haurbano.presentation.common

import com.haurbano.domain.common.ErrorEntity

interface ErrorMessageProvider {
    fun getMessageFor(error: ErrorEntity?): String
}