package com.haurbano.presentation.common

import android.content.Context
import com.haurbano.domain.common.ErrorEntity
import com.haurbano.presentation.R

class ErrorMessageProviderImpl(
    private val context: Context
) : ErrorMessageProvider {

    override fun getMessageFor(error: ErrorEntity?): String {
            return when (error) {
                ErrorEntity.NetworkError -> getString(R.string.msg_network_error)
                null -> getString(R.string.msg_unknown_error)
                else -> getString(R.string.msg_unknown_error)
            }
    }

    private fun getString(resource: Int): String {
        return context.getString(resource)
    }
}