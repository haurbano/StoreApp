package com.haurbano.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.haurbano.domain.common.Status
import com.haurbano.domain.models.ProductDetails
import com.haurbano.presentation.R
import com.haurbano.presentation.common.TransitionStatus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : AppCompatActivity() {

    private val viewModel: ProductDetailViewModel by viewModel()
    private var imageTransitionStatus: TransitionStatus = TransitionStatus.UNDEFINED
    private val pendingActions = ArrayList<()-> Unit>()

    companion object {
        const val MAIN_IMAGE_TRANSACTION_KEY = "top_image"
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
        ViewCompat.setTransitionName(productDetailImage, MAIN_IMAGE_TRANSACTION_KEY)
        showTemporalThumbnail()
        listenProductDetailsChanges()
        fetchProductDetails()
        addTransactionListener()
    }

    private fun showTemporalThumbnail() {
        val thumbnail = intent.getStringExtra(THUMBNAIL_KEY)
        displayProductImage(thumbnail)
    }

    private fun addTransactionListener() {
        val transaction = window.sharedElementEnterTransition
        transaction?.let {
            transaction.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(p0: Transition?) {
                    pendingActions.forEach { it() }
                    imageTransitionStatus = TransitionStatus.FINISHED
                    transaction.removeListener(this)
                }

                override fun onTransitionResume(p0: Transition?) {}

                override fun onTransitionPause(p0: Transition?) {}

                override fun onTransitionCancel(p0: Transition?) {
                    transaction.removeListener(this)
                }

                override fun onTransitionStart(p0: Transition?) {
                    imageTransitionStatus = TransitionStatus.STARTED
                }
            })
        }
    }

    private fun fetchProductDetails() {
        val productId = intent.getStringExtra(PRODUCT_ID_KEY)
        viewModel.fetchProductDetails(productId)
    }

    private fun listenProductDetailsChanges() {
        viewModel.getProduct().observe(this, Observer { product ->
            when (product.status) {
                Status.SUCCESS -> renderProductDetails(product.data)
            }
        })
    }

    private fun renderProductDetails(details: ProductDetails?) {
        details?.let {
            replaceThumbnail(it.images.first())
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