package com.haurbano.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.haurbano.domain.models.ProductDetails
import com.haurbano.presentation.common.composes.LoadingScreen
import com.haurbano.presentation.common.displayPrice

@Composable
fun ProductDetailsScreen(productVM: ProductDetailViewModel){
    val uiState by productVM.uiState.collectAsState()
    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        ProductDetailsContent(productDetail = uiState.productDetails)
    }
}

@Composable fun ProductDetailsContent(
    productDetail: ProductDetails,
    modifier: Modifier = Modifier
) {
    Column {
        Image(painter = rememberImagePainter(productDetail.images.first()), contentDescription = "Image Product")
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Text(text = "${productDetail.condition.capitalize()} - ${productDetail.soldQuantity} Sold")
            Text(text = productDetail.title)
            Text(text = productDetail.price.displayPrice())
        }
    }
}