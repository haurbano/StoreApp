package com.haurbano.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.haurbano.domain.common.Status
import com.haurbano.domain.models.ProductDetails
import com.haurbano.presentation.R
import com.haurbano.presentation.common.ErrorMessageProvider
import com.haurbano.presentation.common.TransitionStatus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_product_detail)
        listenProductDetailsChanges()
        fetchProductDetails()
        showTemporalThumbnail()
    }

    private fun showTemporalThumbnail() {
        val thumbnail = intent.getStringExtra(THUMBNAIL_KEY)
        displayProductImage(thumbnail)
    }

    private fun fetchProductDetails() {
        val productId = intent.getStringExtra(PRODUCT_ID_KEY) ?: ""
        viewModel.fetchProductDetails(productId)
    }

    private fun listenProductDetailsChanges() {
        viewModel.getProduct().observe(this, Observer { product ->
            when (product.status) {
                Status.SUCCESS -> renderProductDetails(product.data)
                Status.LOADING -> showLoadingUI()
                Status.ERROR -> {
                    val message = errorMessageProvider.getMessageFor(product.error)
                    showErrorUI(message)
                }
            }
        })
    }

    private fun showErrorUI(message: String) {
        progressBarDetails.visibility = View.GONE
        productDetailsContainer.visibility = View.GONE
        imgUserFeedbackDetails.visibility = View.VISIBLE
        txtMsgUserFeedbackDetails.visibility = View.VISIBLE

        imgUserFeedbackDetails.setImageResource(R.drawable.ic_error_24)
        txtMsgUserFeedbackDetails.text = message
    }

    private fun showLoadingUI() {
        productDetailsContainer.visibility = View.GONE
        progressBarDetails.visibility = View.VISIBLE
    }

    private fun renderProductDetails(details: ProductDetails?) {
        progressBarDetails.visibility = View.GONE
        productDetailsContainer.visibility = View.VISIBLE
        imgUserFeedbackDetails.visibility = View.GONE
        txtMsgUserFeedbackDetails.visibility = View.GONE

        details?.let {
            replaceThumbnail(it.images.first())
            tvProductDetailsTitle.text = it.title
            txtProductDetailPrice.text = it.price.toString()
        }
    }

    private fun replaceThumbnail(url: String) {
        when (imageTransitionStatus) {
            TransitionStatus.STARTED -> pendingActions.add { displayProductImage(url) }
            else -> displayProductImage(url)
        }
    }

    private fun displayProductImage(url: String?) {
        url?.let {
            Picasso.get()
                .load(it)
                .noFade()
                .noPlaceholder()
                .error(R.drawable.ic_broken_image_24)
                .into(productDetailImage)
        }
    }
}