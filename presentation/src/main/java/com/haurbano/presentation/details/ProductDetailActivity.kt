package com.haurbano.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.haurbano.presentation.R
import com.haurbano.presentation.common.ErrorMessageProvider
import com.haurbano.presentation.common.TransitionStatus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : ComponentActivity() {

    private val viewModel: ProductDetailViewModel by viewModel()
    private val errorMessageProvider: ErrorMessageProvider by inject()
    private var imageTransitionStatus: TransitionStatus = TransitionStatus.UNDEFINED
    private val pendingActions = ArrayList<()-> Unit>()

    companion object {
        private const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"
        private const val THUMBNAIL_KEY = "THUMBNAIL_KEY"

        fun start(
            context: Context,
            productId: String,
            thumbnail: String = "",
            bundleTransaction: Bundle? = null
        ) {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT_ID_KEY, productId)
            intent.putExtra(THUMBNAIL_KEY, thumbnail)
            context.startActivity(intent, bundleTransaction)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            ProductDetailsScreen(productVM = viewModel)
        }
        fetchProductDetails()
    }

    private fun fetchProductDetails() {
        val productId = intent.getStringExtra(PRODUCT_ID_KEY) ?: ""
        viewModel.fetchProductDetails(productId)
    }
}