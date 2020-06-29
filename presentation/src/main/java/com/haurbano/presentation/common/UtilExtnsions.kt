package com.haurbano.presentation.common

import java.text.DecimalFormat

fun Int.displayPrice(): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(this)
}