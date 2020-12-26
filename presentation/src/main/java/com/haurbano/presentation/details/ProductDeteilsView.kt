package com.haurbano.presentation.details

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haurbano.domain.models.ProductDetails
import com.haurbano.presentation.common.composes.NetworkImage
import com.haurbano.presentation.common.displayPrice

@Composable
fun ProductDetailsView(productDetail: ProductDetails) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Header(productDetail = productDetail)
    }
}

@Composable fun Header(
    productDetail: ProductDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth()
    ) {
        NetworkImage(
            url = productDetail.images.first(),
            modifier = modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
        )
        Text(text = "${productDetail.condition.capitalize()} - ${productDetail.soldQuantity} Sold")
        Text(text = productDetail.title)
        Text(text = productDetail.price.displayPrice())
    }
}