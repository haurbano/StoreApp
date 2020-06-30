package com.haurbano.presentation.common

import java.text.DecimalFormat

fun Float.displayPrice(): String {
    val formatter = DecimalFormat("#,###.##")
    return formatter.format(this)
}